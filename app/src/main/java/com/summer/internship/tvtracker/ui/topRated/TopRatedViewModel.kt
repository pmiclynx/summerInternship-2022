package com.summer.internship.tvtracker.ui.topRated

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.summer.internship.tvtracker.data.MoviesRepositoryFactoryIMPL
import com.summer.internship.tvtracker.di.DependencyInjector
import com.summer.internship.tvtracker.domain.Movie
import com.summer.internship.tvtracker.domain.MovieResponseListener

class TopRatedViewModel : ViewModel() {
    private val moviesRepository = DependencyInjector.provideMovieRepository()
    private val movies: MutableLiveData<List<Movie>> by lazy {
        MutableLiveData<List<Movie>>().also {
            loadMovies()
        }
    }

    fun getMovies(): LiveData<List<Movie>> {
        return movies
    }

    private fun loadMovies() {
        moviesRepository.getTopRated(object : MovieResponseListener {
            override fun onMoviesReceived(list: List<Movie>) {
                movies.postValue(list)
            }
        })
    }
}