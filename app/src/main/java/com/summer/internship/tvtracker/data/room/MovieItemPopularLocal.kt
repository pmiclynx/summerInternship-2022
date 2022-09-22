package com.summer.internship.tvtracker.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieItemPopularLocal(
    @PrimaryKey
    val id: Long,
    val title: String,
    val url: String?
)