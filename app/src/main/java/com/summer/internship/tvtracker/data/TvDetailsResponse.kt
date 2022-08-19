package com.summer.internship.tvtracker.data

import com.google.gson.annotations.SerializedName

class TvDetailsResponse(
    @SerializedName("backdrop_path")
    var backdropPath: String,
    @SerializedName("overview")
    val overView: String,
    @SerializedName("poster_path")
    var posterPath: String,
    @SerializedName("vote_average")
    val voteAverage: Float,
    val name: String
) {
}