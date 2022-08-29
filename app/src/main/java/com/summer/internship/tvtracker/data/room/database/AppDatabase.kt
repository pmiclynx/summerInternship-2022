package com.summer.internship.tvtracker.data.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.summer.internship.tvtracker.data.room.Favorite
import com.summer.internship.tvtracker.data.room.MovieItemPopular
import com.summer.internship.tvtracker.data.room.MovieItemTopRated
import com.summer.internship.tvtracker.data.room.dao.FavoriteDao
import com.summer.internship.tvtracker.data.room.dao.MovieItemPopularDao
import com.summer.internship.tvtracker.data.room.dao.MovieItemTopRatedDao

@Database(entities = [Favorite::class,MovieItemPopular::class,MovieItemTopRated::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
    abstract fun movieItemPopularDao(): MovieItemPopularDao
    abstract fun movieItemTopRatedDao(): MovieItemTopRatedDao
}