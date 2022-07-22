package com.summer.internship.tvtracker

class Movie(val title: String, val url: String? , val id:Long) {

    companion object {
        private var lastMovieId = 0
        fun createMovieList(numMovies: Int): List<Movie> {
            val movies :MutableList<Movie> = mutableListOf()
            for (i in 1..numMovies) {
                movies.add(Movie("Title " + ++lastMovieId, "vedem",1))
            }
            return movies
        }
    }
}