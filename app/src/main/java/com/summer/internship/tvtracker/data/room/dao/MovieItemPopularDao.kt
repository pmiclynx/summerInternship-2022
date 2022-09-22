package com.summer.internship.tvtracker.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.summer.internship.tvtracker.data.room.MovieItemPopularLocal
import io.reactivex.rxjava3.core.Single

@Dao
interface MovieItemPopularDao {
    @Query("SELECT * FROM MovieItemPopularLocal")
    fun getAll(): Single<List<MovieItemPopularLocal>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg movies: MovieItemPopularLocal)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movies: List<MovieItemPopularLocal>)

    @Query("DELETE FROM MovieItemPopularLocal")
    fun deleteAll()
}