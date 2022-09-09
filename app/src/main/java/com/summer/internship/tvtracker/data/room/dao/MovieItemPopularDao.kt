package com.summer.internship.tvtracker.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.summer.internship.tvtracker.data.room.MovieItemPopular
import io.reactivex.rxjava3.core.Single

@Dao
interface MovieItemPopularDao {
    @Query("SELECT * FROM MovieItemPopular")
    fun getAll(): Single<List<MovieItemPopular>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg movies: MovieItemPopular)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movies: List<MovieItemPopular>)

    @Query("DELETE FROM MovieItemPopular")
    fun deleteAll()
}