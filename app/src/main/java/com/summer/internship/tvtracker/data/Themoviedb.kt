package com.summer.internship.tvtracker.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Themoviedb {
    @GET("popular")
    fun getPopular(): Call<QuoteList>

    @GET("top_rated")
    fun getTopRated(): Call<QuoteList>

    @GET("{tv_id}")
    fun getMovieDetails(@Path("tv_id") tvId: Long): Call<TvDetailsResponse>
}