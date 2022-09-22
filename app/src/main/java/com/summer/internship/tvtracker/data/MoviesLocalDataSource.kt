package com.summer.internship.tvtracker.data

import com.summer.internship.tvtracker.data.room.FavoriteLocal
import com.summer.internship.tvtracker.data.room.MovieItemPopularLocal
import com.summer.internship.tvtracker.data.room.MovieItemTopRatedLocal
import com.summer.internship.tvtracker.data.room.dao.FavoriteDao
import com.summer.internship.tvtracker.data.room.dao.MovieItemPopularDao
import com.summer.internship.tvtracker.data.room.dao.MovieItemTopRatedDao
import com.summer.internship.tvtracker.domain.LocalDataSource
import io.reactivex.rxjava3.core.Single

class MoviesLocalDataSource(
    private val favoriteDao: FavoriteDao,
    private val popularDao: MovieItemPopularDao,
    private val topRatedDao: MovieItemTopRatedDao
) : LocalDataSource {

    override fun addFavorite(fav: FavoriteLocal) {
        favoriteDao.insert(fav)
    }

    override fun addPopular(pop: List<MovieItemPopularLocal>) {
        popularDao.insert(pop)
    }

    override fun deleteAllPopular() {
        popularDao.deleteAll()
    }

    override fun getPopular(): Single<List<MovieItemPopularLocal>> {
        return popularDao.getAll()
    }

    override fun getTopRated(): Single<List<MovieItemTopRatedLocal>> {
        return topRatedDao.getAll()
    }

    override fun getFavorites(): Single<List<FavoriteLocal>> {
        return favoriteDao.getAll()
    }

    override fun addTopRated(top: List<MovieItemTopRatedLocal>) {
        topRatedDao.insert(top)
    }

    override fun deleteAllTopRated() {
        topRatedDao.deleteAll()
    }

    override fun deleteFavorite(id: Long) {
        Thread {
            favoriteDao.deleteWithId(id)
        }.start()

    }


}