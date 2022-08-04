package com.summer.internship.tvtracker.Domain

import com.summer.internship.tvtracker.Domain.MovieResponseListener

interface MoviesDataSource {
    fun getPopular(movieResponseListener: MovieResponseListener)
    fun getTopRated(movieResponseListener: MovieResponseListener)
}