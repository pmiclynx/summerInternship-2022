package com.summer.internship.tvtracker

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Themoviedb {
    @GET("popular")
    fun getPopular(@Query("api_key") key:String): Call<QuoteList>

    @GET("top_rated")
    fun getTopRated(@Query("api_key") key:String): Call<QuoteList>
}