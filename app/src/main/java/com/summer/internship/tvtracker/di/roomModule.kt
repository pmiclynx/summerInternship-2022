package com.summer.internship.tvtracker.di

import androidx.room.Room
import com.summer.internship.tvtracker.data.room.database.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val roomModule = module {

    single {
        Room.databaseBuilder(androidContext(), AppDatabase::class.java, "Favorite")
            .build()
    }

    single { get<AppDatabase>().favoriteDao() }
    single { get<AppDatabase>().movieItemPopularDao() }
    single { get<AppDatabase>().movieItemTopRatedDao() }
}