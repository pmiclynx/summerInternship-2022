package com.summer.internship.tvtracker.data

import com.summer.internship.tvtracker.domain.DetailsResponseListener
import com.summer.internship.tvtracker.domain.MovieResponseListener
import com.summer.internship.tvtracker.domain.MoviesDataSource
import com.summer.internship.tvtracker.domain.MoviesRepository

class MoviesRepositoryIMPL(private val moviesDataSource: MoviesDataSource) :
    MoviesRepository {
    override fun getPopular(movieResponseListener: MovieResponseListener) {
        moviesDataSource.getPopular(movieResponseListener)
    }

    override fun getTopRated(movieResponseListener: MovieResponseListener) {
        moviesDataSource.getTopRated(movieResponseListener)
    }

    override fun getMovieDetails(id: Long, detailsResponseListener: DetailsResponseListener) {
        moviesDataSource.getMovieDetails(id,detailsResponseListener)
    }
}