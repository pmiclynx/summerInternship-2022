package com.summer.internship.tvtracker.domain

interface MoviesDataSource {
    fun getPopular(movieResponseListener: MovieResponseListener)
    fun getTopRated(movieResponseListener: MovieResponseListener)
    fun getMovieDetails(id: Long, detailsResponseListener: DetailsResponseListener)
}