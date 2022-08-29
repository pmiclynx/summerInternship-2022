package com.summer.internship.tvtracker.di

import com.summer.internship.tvtracker.data.MoviesRepositoryIMPL
import com.summer.internship.tvtracker.domain.MoviesRepository

object DependencyInjector {

    @Volatile
    private var instance: MoviesRepository? = null

    @Synchronized
    fun provideMovieRepository(): MoviesRepository {
        if (instance == null) {
            synchronized(this) {
                if (instance === null) {
                    instance = MoviesRepositoryIMPL(
                        DataProvider.provideRemoteMovieDataSource(),
                        DataProvider.provideLocalMovieDataSource()
                    )
                }
            }
        }
        return instance as MoviesRepositoryIMPL
    }
}