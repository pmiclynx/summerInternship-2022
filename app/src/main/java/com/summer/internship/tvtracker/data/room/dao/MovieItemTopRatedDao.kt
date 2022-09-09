package com.summer.internship.tvtracker.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.summer.internship.tvtracker.data.room.MovieItemPopular
import com.summer.internship.tvtracker.data.room.MovieItemTopRated
import io.reactivex.rxjava3.core.Single

@Dao
interface MovieItemTopRatedDao {
    @Query("SELECT * FROM MovieItemTopRated")
    fun getAll(): Single<List<MovieItemTopRated>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg movies: MovieItemTopRated)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movies: List<MovieItemTopRated>)

    @Query("DELETE FROM MovieItemTopRated")
    fun deleteAll()
}