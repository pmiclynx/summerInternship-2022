package com.summer.internship.tvtracker.data

import android.util.Log
import com.summer.internship.tvtracker.data.room.Favorite
import com.summer.internship.tvtracker.data.room.MovieItemPopular
import com.summer.internship.tvtracker.data.room.MovieItemTopRated
import com.summer.internship.tvtracker.domain.*
import com.summer.internship.tvtracker.domain.details.OnAddListener


class MoviesRepositoryIMPL(
    private val moviesDataSource: MoviesDataSource,
    private val moviesLocalDataSource: MoviesLocalDataSource
) :
    MoviesRepository {
    override fun getPopular(movieResponseListener: MovieResponseListener) {
        moviesDataSource.getPopular(object : MovieResponseListener {
            override fun onMoviesReceived(list: List<Movie>) {
                moviesLocalDataSource.deleteAllPopular()
                moviesLocalDataSource.addPopular(list.map { mov ->
                    MovieItemPopular(
                        mov.id,
                        mov.title,
                        mov.url
                    )
                })
                movieResponseListener.onMoviesReceived(list)
            }

            override fun onError(e: Throwable) {
                moviesLocalDataSource.getAllPopular(object : PopularMovieResponseListener {
                    override fun onMoviesReceived(list: List<MovieItemPopular>) {
                        Log.d("myTag", list.toString())
                        movieResponseListener.onMoviesReceived(list.map { mov ->
                            Movie(
                                mov.title,
                                mov.url,
                                mov.id
                            )
                        })
                        Log.d("myTag", "da")
                    }

                })

            }

        })
    }

    override fun getTopRated(movieResponseListener: MovieResponseListener) {
        moviesDataSource.getTopRated(object : MovieResponseListener {
            override fun onMoviesReceived(list: List<Movie>) {
                moviesLocalDataSource.deleteAllTopRated()
                moviesLocalDataSource.addTopRated(list.map { mov ->
                    MovieItemTopRated(
                        mov.id,
                        mov.title,
                        mov.url
                    )
                })
                movieResponseListener.onMoviesReceived(list)
            }

            override fun onError(e: Throwable) {
                moviesLocalDataSource.getAllTopRated(object : TopRatedMovieResponseListener {
                    override fun onMoviesReceived(list: List<MovieItemTopRated>) {
                        Log.d("myTag", list.toString())
                        movieResponseListener.onMoviesReceived(list.map { mov ->
                            Movie(
                                mov.title,
                                mov.url,
                                mov.id
                            )
                        })
                        Log.d("myTag", "da")
                    }

                })

            }

        })
    }

    override fun getMovieDetails(id: Long, detailsResponseListener: DetailsResponseListener) {
        moviesDataSource.getMovieDetails(id, detailsResponseListener)
    }

    override fun addFavorite(
        detailsResponse: TvDetailsResponse,
        id: Long?,
        onAddListener: OnAddListener
    ) {
        moviesDataSource.addFavorite(detailsResponse, id, onAddListener)
//        moviesLocalDataSource.context = appContext
        id?.let {
            val fav = Favorite(
                id,
                detailsResponse.backdropPath,
                detailsResponse.overView,
                detailsResponse.posterPath,
                detailsResponse.voteAverage,
                detailsResponse.name
            )
            Log.d("aaa", fav.toString())
            moviesLocalDataSource.addFavorite(fav)
        }


    }
}