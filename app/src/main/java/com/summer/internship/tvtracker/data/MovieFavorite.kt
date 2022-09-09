package com.summer.internship.tvtracker.data

import com.google.gson.annotations.SerializedName

class MovieFavorite(
    var backdropPath: String,
    val overView: String,
    var posterPath: String,
    val voteAverage: Float,
    val name: String,
    val date:String
) {

}