package com.summer.internship.tvtracker.domain

interface MovieResponseListener {
    fun onMoviesReceived(list: List<Movie>)
    fun onError (){

    }
}