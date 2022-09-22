package com.summer.internship.tvtracker.data

import com.summer.internship.tvtracker.domain.*
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.functions.BiFunction
import io.reactivex.rxjava3.schedulers.Schedulers


class MoviesRepositoryIMPL(
    private val moviesDataSource: RemoteDataSource,
    private val moviesLocalDataSource: LocalDataSource,
    private val favoritesDataSource: FavoritesDataSource
) :
    MoviesRepository {
    override fun getPopular(): Single<List<MovieUI>> {
        return singleZip(moviesDataSource.getPopular()).map {
            moviesLocalDataSource.deleteAllPopular()
            moviesLocalDataSource.addPopular(it.map { popularMovie ->
                RemoteMovieMapper.mapToPopularRoom(popularMovie)
            })
            it
        }.onErrorResumeNext {
            moviesLocalDataSource.getPopular().map { popularMovieList ->
                popularMovieList.map {
                    RemoteMovieMapper.mapFromPopularRoom(it)
                }
            }
        }
    }

    private fun singleZip(movies: Single<QuoteList>) = Single.zip(
        movies.subscribeOn(Schedulers.io()).observeOn(Schedulers.io()),
        moviesDataSource.getConfig().subscribeOn(Schedulers.io()),
        BiFunction { firstResponse: QuoteList,
                     secondResponse: ConfigResponse ->
            firstResponse.results.map {
                it.backdrop_path =
                    secondResponse.images.secure_base_url + secondResponse.images.backdrop_sizes[1] + it.backdrop_path
                RemoteMovieMapper.mapToEntity(it)
            }
        })

    override fun getTopRated(): Single<List<MovieUI>> {
        return singleZip(moviesDataSource.getTopRated()).map {
            moviesLocalDataSource.deleteAllTopRated()
            moviesLocalDataSource.addTopRated(it.map { topRatedMovie ->
                RemoteMovieMapper.mapToTopRatedRoom(topRatedMovie)
            })
            it
        }.onErrorResumeNext {
            moviesLocalDataSource.getTopRated().map { topRatedMovieList ->
                topRatedMovieList.map {
                    RemoteMovieMapper.mapFromTopRatedRoom(it)
                }
            }
        }
    }

    override fun getMovieDetails(id: Long): Single<TvDetailsUi> {
        return Single.zip(
            moviesDataSource.getMovieDetails(id),
            moviesDataSource.getConfig().subscribeOn(Schedulers.io()),
            BiFunction { firstResponse: TvDetailsResponse,
                         secondResponse: ConfigResponse ->
                val url = firstResponse.backdropPath
                val posterUrl = firstResponse.posterPath
                firstResponse.backdropPath =
                    secondResponse.images.secure_base_url + secondResponse.images.backdrop_sizes[1] + url
                firstResponse.posterPath =
                    secondResponse.images.secure_base_url + secondResponse.images.backdrop_sizes[1] + posterUrl
                TvDetailsMapper.mapToEntity(firstResponse)
            })
    }

    override fun getConfig(): Single<ConfigResponse> {
        return moviesDataSource.getConfig()
    }

    override fun addFavorite(
        detailsResponse: TvDetailsUi,
        id: Long?
    ): Single<String> {
        return favoritesDataSource.addFavorite(detailsResponse, id).observeOn(Schedulers.io()).map {
            id?.let {
                moviesLocalDataSource.addFavorite(
                    RemoteFavoriteMovieMapper.mapToLocalFromDetailsResponse(
                        id,
                        detailsResponse
                    )
                )
            }
            it
        }
    }

    override fun getFavorites(): Single<List<FavoriteMovieUI>> {
        return favoritesDataSource.getFavorites().map {
            it
        }.onErrorResumeNext {
            moviesLocalDataSource.getFavorites().map { favoriteMovieList ->
                favoriteMovieList.map {
                    RemoteFavoriteMovieMapper.mapToEntity(it)
                }
            }
        }
    }

    override fun deleteFavorite(id: Long) {
        favoritesDataSource.deleteFavorite(id)
        moviesLocalDataSource.deleteFavorite(id)
    }


}