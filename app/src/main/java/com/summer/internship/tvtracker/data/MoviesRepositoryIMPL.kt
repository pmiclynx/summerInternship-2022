package com.summer.internship.tvtracker.data

import android.util.Log
import com.summer.internship.tvtracker.data.room.Favorite
import com.summer.internship.tvtracker.data.room.MovieItemPopular
import com.summer.internship.tvtracker.data.room.MovieItemTopRated
import com.summer.internship.tvtracker.domain.*
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter


class MoviesRepositoryIMPL(
    private val moviesDataSource: MoviesDataSource,
    private val moviesLocalDataSource: MoviesLocalDataSource
) :
    MoviesRepository {
    override fun getPopular(): Single<List<Movie>> {
        return moviesDataSource.getPopular().map {
            moviesLocalDataSource.deleteAllPopular()
            moviesLocalDataSource.addPopular(it.results.map { popularMovie ->
                MovieItemPopular(
                    popularMovie.id,
                    popularMovie.name,
                    "https://image.tmdb.org/t/p/w500" + popularMovie.backdrop_path
                )
            })
            it.results.map { result ->
                Movie(
                    result.name,
                    "https://image.tmdb.org/t/p/w500" + result.backdrop_path,
                    result.id
                )
            }
        }.onErrorResumeNext {
            moviesLocalDataSource.getAllPopular().map { popularMovieList ->
                popularMovieList.map {
                    Movie(
                        it.title,
                        it.url,
                        it.id
                    )
                }
            }
        }
    }

    override fun getTopRated(): Single<List<Movie>> {
        Log.d("airb",Thread.currentThread().toString())
        return moviesDataSource.getTopRated().map {
            Log.d("airb",Thread.currentThread().toString())
            moviesLocalDataSource.deleteAllTopRated()
            moviesLocalDataSource.addTopRated(it.results.map { topRatedMovie ->
                MovieItemTopRated(
                    topRatedMovie.id,
                    topRatedMovie.name,
                    "https://image.tmdb.org/t/p/w500" + topRatedMovie.backdrop_path
                )
            })
            it.results.map { result ->
                Movie(
                    result.name,
                    "https://image.tmdb.org/t/p/w500" + result.backdrop_path,
                    result.id
                )
            }
        }.onErrorResumeNext {
            Log.d("airb","daaaa")
            moviesLocalDataSource.getAllTopRated().map { topRatedMovieList ->
                topRatedMovieList.map {
                    Movie(
                        it.title,
                        it.url,
                        it.id
                    )
                }
            }
        }
    }

    override fun getMovieDetails(id: Long): Single<TvDetailsResponse> {
        return moviesDataSource.getMovieDetails(id)
    }

    override fun addFavorite(
        detailsResponse: TvDetailsResponse,
        id: Long?
    ): Single<String> {
        Log.d("airb",Thread.currentThread().toString())
        return moviesDataSource.addFavorite(detailsResponse, id).map {
            Log.d("airb",Thread.currentThread().toString())
            id?.let {
                val favoriteMovie = Favorite(
                    id,
                    detailsResponse.backdropPath,
                    detailsResponse.overView,
                    detailsResponse.posterPath,
                    detailsResponse.voteAverage,
                    detailsResponse.name,
                    DateTimeFormatter
                        .ofPattern("yyyy-MM-dd")
                        .withZone(ZoneOffset.UTC)
                        .format(Instant.now())
                )
                Log.d("aaa", favoriteMovie.toString())
                moviesLocalDataSource.addFavorite(favoriteMovie)
            }
            it
        }
    }

    override fun getFavorites(): Single<List<FavoriteMovie>> {
        return moviesDataSource.getFavorites().map {
            Log.d("aitag1", "nu")
            it
        }.onErrorResumeNext {
            Log.d("aitag1", "daa")
            moviesLocalDataSource.getAllFavorites().map { favoriteMovieList ->
                favoriteMovieList.map {
                    FavoriteMovie(
                        it.name,
                        it.backdropPath,
                        it.date,
                        it.id.toString()
                    )
                }
            }
        }
    }

    override fun deleteFavorite(id: String) {
        moviesDataSource.deleteFavorite(id)
        moviesLocalDataSource.deleteFavorite(id.toLong())
    }


}