package com.summer.internship.tvtracker.data

import com.google.gson.annotations.SerializedName

class MovieRemote(
    @SerializedName("backdrop_path")
    var backdrop_path: String,
    val id: Long,
    val name: String,
)

