package com.summer.internship.tvtracker.ui.popular

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.summer.internship.tvtracker.data.MoviesRepositoryFactoryIMPL
import com.summer.internship.tvtracker.domain.Movie
import com.summer.internship.tvtracker.domain.MovieResponseListener

class PopularViewModel : ViewModel() {
    private val moviesRepository = MoviesRepositoryFactoryIMPL.createMoviesRepository()
    private val movies: MutableLiveData<List<Movie>> by lazy {
        MutableLiveData<List<Movie>>().also {
            loadMovies()
        }
    }

    fun getMovies(): LiveData<List<Movie>> {
        return movies
    }

    private fun loadMovies() {
        moviesRepository.getPopular(object : MovieResponseListener {
            override fun onMoviesReceived(list: List<Movie>) {
                movies.postValue(list)
            }

            override fun onError() {

            }

        })
    }
}