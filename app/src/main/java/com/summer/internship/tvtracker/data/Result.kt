package com.summer.internship.tvtracker.data

import com.google.gson.annotations.SerializedName

class Result(
    @SerializedName("backdrop_path")
    val backdrop_path: String,
    val id: Long,
    val name: String,
)

