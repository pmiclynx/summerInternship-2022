package com.summer.internship.tvtracker.domain

import com.summer.internship.tvtracker.data.ConfigResponse
import com.summer.internship.tvtracker.data.QuoteList
import com.summer.internship.tvtracker.data.TvDetailsResponse
import com.summer.internship.tvtracker.data.TvDetailsUi
import io.reactivex.rxjava3.core.Single

interface RemoteDataSource  {
    fun getPopular(): Single<QuoteList>
    fun getTopRated(): Single<QuoteList>
    fun getMovieDetails(id: Long): Single<TvDetailsResponse>
    fun getConfig(): Single<ConfigResponse>
}