package com.summer.internship.tvtracker.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.summer.internship.tvtracker.data.room.Favorite
import io.reactivex.rxjava3.core.Single

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM Favorite")
    fun getAll(): Single<List<Favorite>>

    @Insert(onConflict = REPLACE)
    fun insert(vararg favorites: Favorite)

    @Insert(onConflict = REPLACE)
    fun insert(favorites: List<Favorite>)

    @Delete
    fun delete(fav: Favorite)

    @Query("DELETE FROM MovieItemTopRated WHERE id=:id")
    fun deleteWithId(id:Long)
}