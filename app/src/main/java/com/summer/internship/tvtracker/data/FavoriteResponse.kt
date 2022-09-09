package com.summer.internship.tvtracker.data


data class FavoriteResponse(
    var backdropPath: String?=null,
    val overView: String?=null,
    var posterPath: String?=null,
    val voteAverage: Float?=null,
    val name: String?=null,
    val date:String?=null
)