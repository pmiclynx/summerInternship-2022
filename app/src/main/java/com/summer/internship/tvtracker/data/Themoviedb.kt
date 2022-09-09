package com.summer.internship.tvtracker.data

import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Themoviedb {
    @GET("popular")
    fun getPopular(): Single<QuoteList>

    @GET("top_rated")
    fun getTopRated(): Single<QuoteList>

    @GET("{tv_id}")
    fun getMovieDetails(@Path("tv_id") tvId: Long): Single<TvDetailsResponse>
}