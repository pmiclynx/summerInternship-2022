package com.summer.internship.tvtracker.domain

interface MoviesRepository {
    fun getPopular(movieResponseListener: MovieResponseListener)
    fun getTopRated(movieResponseListener: MovieResponseListener)
    fun getMovieDetails(id: Long, detailsResponseListener: DetailsResponseListener)
}