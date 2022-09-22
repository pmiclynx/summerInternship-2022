package com.summer.internship.tvtracker.domain

import com.summer.internship.tvtracker.data.ConfigResponse
import com.summer.internship.tvtracker.data.TvDetailsResponse
import com.summer.internship.tvtracker.data.TvDetailsUi
import io.reactivex.rxjava3.core.Single

interface MoviesRepository {
    fun getPopular():Single<List<MovieUI>>
    fun getTopRated():Single<List<MovieUI>>
    fun getMovieDetails(id: Long):Single<TvDetailsUi>
    fun getConfig():Single<ConfigResponse>
    fun addFavorite(
        detailsResponse: TvDetailsUi,
        id: Long?
    ):Single<String>
    fun getFavorites():Single<List<FavoriteMovieUI>>
    fun deleteFavorite(id:Long)
}