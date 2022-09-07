package com.summer.internship.tvtracker.di

import android.content.Context
import androidx.room.Room
import com.summer.internship.tvtracker.data.room.dao.FavoriteDao
import com.summer.internship.tvtracker.data.room.dao.MovieItemPopularDao
import com.summer.internship.tvtracker.data.room.dao.MovieItemTopRatedDao
import com.summer.internship.tvtracker.data.room.database.AppDatabase

object RoomModule {

    private lateinit var applicationContext: () -> Context

    fun initApplicationContext(provideContext: () -> Context) {
        applicationContext = provideContext
    }

    private fun provideMovieRoomDatabase(): AppDatabase = Room.databaseBuilder(
        applicationContext(),
        AppDatabase::class.java, "Favorite"
    ).build()

    fun provideFavDao(): FavoriteDao = provideMovieRoomDatabase().favoriteDao()

    fun provideMovieItemPopularDao(): MovieItemPopularDao =
        provideMovieRoomDatabase().movieItemPopularDao()

    fun provideMovieItemTopRatedDao(): MovieItemTopRatedDao =
        provideMovieRoomDatabase().movieItemTopRatedDao()
}