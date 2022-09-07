package com.summer.internship.tvtracker.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.summer.internship.tvtracker.data.room.Favorite

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM Favorite")
    fun getAll(): List<Favorite>

    @Insert(onConflict = REPLACE)
    fun insert(vararg favorites: Favorite)

    @Insert(onConflict = REPLACE)
    fun insert(favorites: List<Favorite>)

    @Delete
    fun delete(fav: Favorite)
}