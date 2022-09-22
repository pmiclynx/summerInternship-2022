package com.summer.internship.tvtracker.di

import com.summer.internship.tvtracker.data.MoviesLocalDataSource
import com.summer.internship.tvtracker.data.MoviesRemoteRetrofitDataSource

object DataProvider {
    @Volatile
    private var localInstance: MoviesLocalDataSource? = null

    @Volatile
    private var remoteInstance: MoviesRemoteRetrofitDataSource? = null

    @Synchronized
    fun provideLocalMovieDataSource(): MoviesLocalDataSource {
        if (localInstance == null) {
            synchronized(this) {
                if (localInstance === null) {
                    localInstance = MoviesLocalDataSource(
                        RoomModule11.provideFavDao(),
                        RoomModule11.provideMovieItemPopularDao(),
                        RoomModule11.provideMovieItemTopRatedDao()
                    )
                }
            }
        }
        return localInstance as MoviesLocalDataSource
    }

    @Synchronized
    fun provideRemoteMovieDataSource(): MoviesRemoteRetrofitDataSource {
        if (remoteInstance == null) {
            synchronized(this) {
                if (remoteInstance === null) {
//                    remoteInstance = MoviesRemoteRetrofitDataSource()
                }
            }
        }
        return remoteInstance as MoviesRemoteRetrofitDataSource
    }
}