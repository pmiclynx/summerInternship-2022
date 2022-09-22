package com.summer.internship.tvtracker.domain

import com.summer.internship.tvtracker.data.room.FavoriteLocal
import com.summer.internship.tvtracker.data.room.MovieItemPopularLocal
import com.summer.internship.tvtracker.data.room.MovieItemTopRatedLocal
import io.reactivex.rxjava3.core.Single

interface LocalDataSource:DataSource<FavoriteLocal> {

    fun addFavorite(fav: FavoriteLocal)

    fun addPopular(pop: List<MovieItemPopularLocal>)

    fun deleteAllPopular()

    fun getPopular(): Single<List<MovieItemPopularLocal>>

    fun getTopRated(): Single<List<MovieItemTopRatedLocal>>

    fun addTopRated(top: List<MovieItemTopRatedLocal>)

    fun deleteAllTopRated()

}