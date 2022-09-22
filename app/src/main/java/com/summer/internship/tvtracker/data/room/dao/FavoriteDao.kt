package com.summer.internship.tvtracker.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.summer.internship.tvtracker.data.room.FavoriteLocal
import io.reactivex.rxjava3.core.Single

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM FavoriteLocal")
    fun getAll(): Single<List<FavoriteLocal>>

    @Insert(onConflict = REPLACE)
    fun insert(vararg favorites: FavoriteLocal)

    @Insert(onConflict = REPLACE)
    fun insert(favorites: List<FavoriteLocal>)

    @Delete
    fun delete(fav: FavoriteLocal)

    @Query("DELETE FROM MovieItemTopRatedLocal WHERE id=:id")
    fun deleteWithId(id:Long)
}