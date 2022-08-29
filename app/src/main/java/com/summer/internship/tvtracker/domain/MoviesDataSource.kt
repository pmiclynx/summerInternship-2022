package com.summer.internship.tvtracker.domain

import com.summer.internship.tvtracker.data.TvDetailsResponse
import com.summer.internship.tvtracker.domain.details.OnAddListener

interface MoviesDataSource {
    fun getPopular(movieResponseListener: MovieResponseListener)
    fun getTopRated(movieResponseListener: MovieResponseListener)
    fun getMovieDetails(id: Long, detailsResponseListener: DetailsResponseListener)
    fun addFavorite(detailsResponse: TvDetailsResponse,id:Long?, onAddListener: OnAddListener)
}