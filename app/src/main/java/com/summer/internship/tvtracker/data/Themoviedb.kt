package com.summer.internship.tvtracker.data

import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Themoviedb {
    @GET("tv/popular")
    fun getPopular(): Single<QuoteList>

    @GET("tv/top_rated")
    fun getTopRated(): Single<QuoteList>

    @GET("tv/{tv_id}")
    fun getMovieDetails(@Path("tv_id") tvId: Long): Single<TvDetailsResponse>

    @GET("configuration")
    fun getConfig():Single<ConfigResponse>
}