package com.summer.internship.tvtracker.domain

import com.summer.internship.tvtracker.data.room.MovieItemTopRatedLocal

interface TopRatedMovieResponseListener {
    fun onMoviesReceived(list: List<MovieItemTopRatedLocal>)
    fun onError(e: Throwable) {

    }
}