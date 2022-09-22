package com.summer.internship.tvtracker.domain

import com.summer.internship.tvtracker.data.room.MovieItemPopularLocal

interface PopularMovieResponseListener {
    fun onMoviesReceived(list: List<MovieItemPopularLocal>)
    fun onError(e: Throwable) {

    }
}