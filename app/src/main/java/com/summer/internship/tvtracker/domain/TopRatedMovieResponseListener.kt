package com.summer.internship.tvtracker.domain

import com.summer.internship.tvtracker.data.room.MovieItemTopRated

interface TopRatedMovieResponseListener {
    fun onMoviesReceived(list: List<MovieItemTopRated>)
    fun onError(e: Throwable) {

    }
}