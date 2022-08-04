package com.summer.internship.tvtracker.Domain

interface MovieResponseListener {
    fun onMoviesReceived(list: List<Movie>)
    fun onError ()
}