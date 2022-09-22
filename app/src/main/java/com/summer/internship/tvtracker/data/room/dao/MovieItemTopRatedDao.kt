package com.summer.internship.tvtracker.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.summer.internship.tvtracker.data.room.MovieItemTopRatedLocal
import io.reactivex.rxjava3.core.Single

@Dao
interface MovieItemTopRatedDao {
    @Query("SELECT * FROM MovieItemTopRatedLocal")
    fun getAll(): Single<List<MovieItemTopRatedLocal>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg movies: MovieItemTopRatedLocal)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movies: List<MovieItemTopRatedLocal>)

    @Query("DELETE FROM MovieItemTopRatedLocal")
    fun deleteAll()
}