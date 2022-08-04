package com.summer.internship.tvtracker.Data

import com.summer.internship.tvtracker.Domain.MovieResponseListener
import com.summer.internship.tvtracker.Domain.MoviesDataSource
import com.summer.internship.tvtracker.Domain.MoviesRepository

class MoviesRepositoryIMPL(private val moviesRemoteDataSource: MoviesDataSource) :
    MoviesRepository {
   override fun getPopular(movieResponseListener: MovieResponseListener){
        moviesRemoteDataSource.getPopular(movieResponseListener)
    }
   override fun getTopRated(movieResponseListener: MovieResponseListener){
        moviesRemoteDataSource.getTopRated(movieResponseListener)
    }
}