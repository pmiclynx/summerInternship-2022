package com.summer.internship.tvtracker.domain

import com.summer.internship.tvtracker.data.room.MovieItemPopular

interface PopularMovieResponseListener {
    fun onMoviesReceived(list: List<MovieItemPopular>)
    fun onError(e: Throwable) {

    }
}