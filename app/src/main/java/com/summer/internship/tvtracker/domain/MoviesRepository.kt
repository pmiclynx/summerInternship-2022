package com.summer.internship.tvtracker.domain

import android.content.Context
import com.summer.internship.tvtracker.data.QuoteList
import com.summer.internship.tvtracker.data.TvDetailsResponse
import com.summer.internship.tvtracker.domain.details.OnAddListener
import io.reactivex.rxjava3.core.Single

interface MoviesRepository {
    fun getPopular():Single<List<Movie>>
    fun getTopRated():Single<List<Movie>>
    fun getMovieDetails(id: Long):Single<TvDetailsResponse>
    fun addFavorite(
        detailsResponse: TvDetailsResponse,
        id: Long?
    ):Single<String>
    fun getFavorites():Single<List<FavoriteMovie>>
    fun deleteFavorite(id:String)
}