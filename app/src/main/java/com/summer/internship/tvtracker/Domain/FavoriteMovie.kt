package com.summer.internship.tvtracker.Domain

import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

class FavoriteMovie(val title: String, val url: String, val data: String) {

    companion object {
        private var lastMovieId = 0
        fun createMovieList(numMovies: Int): ArrayList<FavoriteMovie> {
            val movies = ArrayList<FavoriteMovie>()
            for (i in 1..numMovies) {
                movies.add(
                    FavoriteMovie(
                        "Title " + ++lastMovieId, "vedem", DateTimeFormatter
                            .ofPattern("yyyy-MM-dd")
                            .withZone(ZoneOffset.UTC)
                            .format(Instant.now())
                    )
                )
            }
            return movies
        }
    }
}