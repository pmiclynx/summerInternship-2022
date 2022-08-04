package com.summer.internship.tvtracker.Domain

import com.summer.internship.tvtracker.Domain.MoviesDataSource
import com.summer.internship.tvtracker.Domain.MoviesRepository

interface MoviesRepositoryFactory {
    fun createMoviesRepository(moviesRemoteDataSourceInterface: MoviesDataSource): MoviesRepository
}