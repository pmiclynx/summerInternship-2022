package com.summer.internship.tvtracker.ui.detailsScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.summer.internship.tvtracker.data.MoviesRepositoryFactoryIMPL
import com.summer.internship.tvtracker.data.TvDetailsResponse
import com.summer.internship.tvtracker.domain.DetailsResponseListener

class DetailsViewModel() : ViewModel() {
    private val moviesRepository = MoviesRepositoryFactoryIMPL.createMoviesRepository()
    private val details: MutableLiveData<TvDetailsResponse> by lazy {
        MutableLiveData<TvDetailsResponse>()
    }

    fun getDetails(): LiveData<TvDetailsResponse> {
        return details
    }

    fun loadDetails(id: Long) {
        moviesRepository.getMovieDetails(id, object : DetailsResponseListener {
            override fun onDetailsReceived(detailsResponse: TvDetailsResponse) {
                details.postValue(detailsResponse)
            }

        })
    }
}