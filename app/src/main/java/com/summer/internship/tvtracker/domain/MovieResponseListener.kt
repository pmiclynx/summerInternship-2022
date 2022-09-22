package com.summer.internship.tvtracker.domain

interface MovieResponseListener {
    fun onMoviesReceived(list: List<MovieUI>)
    fun onError (e:Throwable){

    }
}