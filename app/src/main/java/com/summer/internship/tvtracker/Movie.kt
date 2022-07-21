package com.summer.internship.tvtracker

class Movie(val title: String, val url: String) {

    companion object {
        private var lastMovieId = 0
        fun createMovieList(numMovies: Int): ArrayList<Movie> {
            val movies = ArrayList<Movie>()
            for (i in 1..numMovies) {
                movies.add(Movie("Title " + ++lastMovieId, "vedem"))
            }
            return movies
        }
    }
}