package com.summer.internship.tvtracker.data.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.summer.internship.tvtracker.data.room.FavoriteLocal
import com.summer.internship.tvtracker.data.room.MovieItemPopularLocal
import com.summer.internship.tvtracker.data.room.MovieItemTopRatedLocal
import com.summer.internship.tvtracker.data.room.dao.FavoriteDao
import com.summer.internship.tvtracker.data.room.dao.MovieItemPopularDao
import com.summer.internship.tvtracker.data.room.dao.MovieItemTopRatedDao

@Database(entities = [FavoriteLocal::class,MovieItemPopularLocal::class,MovieItemTopRatedLocal::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
    abstract fun movieItemPopularDao(): MovieItemPopularDao
    abstract fun movieItemTopRatedDao(): MovieItemTopRatedDao
}