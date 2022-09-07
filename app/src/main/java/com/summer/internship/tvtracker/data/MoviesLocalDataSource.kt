package com.summer.internship.tvtracker.data

import com.summer.internship.tvtracker.data.room.Favorite
import com.summer.internship.tvtracker.data.room.MovieItemPopular
import com.summer.internship.tvtracker.data.room.MovieItemTopRated
import com.summer.internship.tvtracker.data.room.dao.FavoriteDao
import com.summer.internship.tvtracker.data.room.dao.MovieItemPopularDao
import com.summer.internship.tvtracker.data.room.dao.MovieItemTopRatedDao
import com.summer.internship.tvtracker.domain.PopularMovieResponseListener
import com.summer.internship.tvtracker.domain.TopRatedMovieResponseListener

class MoviesLocalDataSource(
    private val favoriteDao: FavoriteDao,
    private val popularDao: MovieItemPopularDao,
    private val topRatedDao: MovieItemTopRatedDao
) {

    fun addFavorite(fav: Favorite) {
        Thread {
            favoriteDao.insert(fav)
        }.start()

    }

    fun addPopular(pop: List<MovieItemPopular>) {
        Thread {
            popularDao.insert(pop)
        }.start()
    }

    fun deleteAllPopular() {
        Thread {
            popularDao.deleteAll()
        }.start()
    }

    fun getAllPopular(popularMovieResponseListener: PopularMovieResponseListener) {
        Thread {
            val list = popularDao.getAll()
            popularMovieResponseListener.onMoviesReceived(list)
        }.start()
    }

    fun getAllTopRated(topRatedMovieResponseListener: TopRatedMovieResponseListener) {
        Thread {
            val list = topRatedDao.getAll()
            topRatedMovieResponseListener.onMoviesReceived(list)
        }.start()
    }

    fun addTopRated(top: List<MovieItemTopRated>) {
        Thread {
            topRatedDao.insert(top)
        }.start()
    }

    fun deleteAllTopRated() {
        Thread {
            topRatedDao.deleteAll()
        }.start()
    }


}