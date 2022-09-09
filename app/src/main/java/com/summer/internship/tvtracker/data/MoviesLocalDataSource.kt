package com.summer.internship.tvtracker.data

import android.util.Log
import com.summer.internship.tvtracker.data.room.Favorite
import com.summer.internship.tvtracker.data.room.MovieItemPopular
import com.summer.internship.tvtracker.data.room.MovieItemTopRated
import com.summer.internship.tvtracker.data.room.dao.FavoriteDao
import com.summer.internship.tvtracker.data.room.dao.MovieItemPopularDao
import com.summer.internship.tvtracker.data.room.dao.MovieItemTopRatedDao
import com.summer.internship.tvtracker.domain.FavoriteMovie
import com.summer.internship.tvtracker.domain.PopularMovieResponseListener
import com.summer.internship.tvtracker.domain.TopRatedMovieResponseListener
import io.reactivex.rxjava3.core.Single

class MoviesLocalDataSource(
    private val favoriteDao: FavoriteDao,
    private val popularDao: MovieItemPopularDao,
    private val topRatedDao: MovieItemTopRatedDao
) {

    fun addFavorite(fav: Favorite) {
        Log.d("aaaaaaa", Thread.currentThread().toString())
        favoriteDao.insert(fav)
    }

    fun addPopular(pop: List<MovieItemPopular>) {
        popularDao.insert(pop)
    }

    fun deleteAllPopular() {
        popularDao.deleteAll()
    }

    fun getAllPopular(): Single<List<MovieItemPopular>> {
        return popularDao.getAll()
    }

    fun getAllTopRated(): Single<List<MovieItemTopRated>> {
        return topRatedDao.getAll()
    }

    fun getAllFavorites(): Single<List<Favorite>> {
        return favoriteDao.getAll()
    }

    fun addTopRated(top: List<MovieItemTopRated>) {
        topRatedDao.insert(top)
    }

    fun deleteAllTopRated() {
        topRatedDao.deleteAll()
    }

    fun deleteFavorite(id: Long) {
        Thread{
            favoriteDao.deleteWithId(id)
        }.start()

    }


}