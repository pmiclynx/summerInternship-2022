package com.summer.internship.tvtracker.di

import com.summer.internship.tvtracker.data.FavoriteFirebaseDataSource
import com.summer.internship.tvtracker.data.MoviesLocalDataSource
import com.summer.internship.tvtracker.data.MoviesRemoteRetrofitDataSource
import com.summer.internship.tvtracker.domain.FavoritesDataSource
import com.summer.internship.tvtracker.domain.LocalDataSource
import com.summer.internship.tvtracker.domain.RemoteDataSource
import org.koin.dsl.module


val dataSourceModule = module {

    single<LocalDataSource> { MoviesLocalDataSource(get(), get(), get()) }
    single<RemoteDataSource> { MoviesRemoteRetrofitDataSource(get()) }
    single<FavoritesDataSource> { FavoriteFirebaseDataSource(get()) }

}
