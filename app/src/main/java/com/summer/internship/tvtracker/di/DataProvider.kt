package com.summer.internship.tvtracker.di

import com.summer.internship.tvtracker.data.MoviesLocalDataSource
import com.summer.internship.tvtracker.data.MoviesRemoteDataSource

object DataProvider {
    @Volatile
    private var localInstance: MoviesLocalDataSource? = null

    @Volatile
    private var remoteInstance: MoviesRemoteDataSource? = null

    @Synchronized
    fun provideLocalMovieDataSource(): MoviesLocalDataSource {
        if (localInstance == null) {
            synchronized(this) {
                if (localInstance === null) {
                    localInstance = MoviesLocalDataSource(
                        RoomModule.provideFavDao(),
                        RoomModule.provideMovieItemPopularDao(),
                        RoomModule.provideMovieItemTopRatedDao()
                    )
                }
            }
        }
        return localInstance as MoviesLocalDataSource
    }

    @Synchronized
    fun provideRemoteMovieDataSource(): MoviesRemoteDataSource {
        if (remoteInstance == null) {
            synchronized(this) {
                if (remoteInstance === null) {
                    remoteInstance = MoviesRemoteDataSource
                }
            }
        }
        return remoteInstance as MoviesRemoteDataSource
    }
}