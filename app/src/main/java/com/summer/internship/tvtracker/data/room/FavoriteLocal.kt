package com.summer.internship.tvtracker.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavoriteLocal(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val backdropPath: String,
    val overView: String,
    val posterPath: String,
    val voteAverage: Float,
    val name: String,
    val date:String
)