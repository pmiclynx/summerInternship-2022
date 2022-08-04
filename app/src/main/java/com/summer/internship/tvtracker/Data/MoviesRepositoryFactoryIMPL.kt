package com.summer.internship.tvtracker.Data

import com.summer.internship.tvtracker.Domain.MoviesDataSource
import com.summer.internship.tvtracker.Domain.MoviesRepository
import com.summer.internship.tvtracker.Domain.MoviesRepositoryFactory

object MoviesRepositoryFactoryIMPL : MoviesRepositoryFactory {
    override fun createMoviesRepository(moviesRemoteDataSourceInterface: MoviesDataSource)
    : MoviesRepository {
        return MoviesRepositoryIMPL(moviesRemoteDataSourceInterface)
    }
}