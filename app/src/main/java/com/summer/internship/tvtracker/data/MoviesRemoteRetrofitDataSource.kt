package com.summer.internship.tvtracker.data

import com.summer.internship.tvtracker.domain.RemoteDataSource
import io.reactivex.rxjava3.core.Single

class MoviesRemoteRetrofitDataSource(private val themoviedb: Themoviedb) :
    RemoteDataSource {


    override fun getPopular(): Single<QuoteList> {
        return themoviedb.getPopular()
    }

    override fun getTopRated(): Single<QuoteList> {
        return themoviedb.getTopRated()
    }

    override fun getMovieDetails(id: Long): Single<TvDetailsResponse> {
        return themoviedb.getMovieDetails(id)
    }

    override fun getConfig(): Single<ConfigResponse> {
        return themoviedb.getConfig()
    }

}