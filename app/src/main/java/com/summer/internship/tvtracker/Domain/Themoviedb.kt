package com.summer.internship.tvtracker.Domain

import retrofit2.Call
import retrofit2.http.GET

interface Themoviedb {
    @GET("popular")
    fun getPopular(): Call<QuoteList>

    @GET("top_rated")
    fun getTopRated(): Call<QuoteList>
}