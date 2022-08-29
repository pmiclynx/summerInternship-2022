package com.summer.internship.tvtracker.domain

import java.lang.Exception

interface MovieResponseListener {
    fun onMoviesReceived(list: List<Movie>)
    fun onError (e:Throwable){

    }
}