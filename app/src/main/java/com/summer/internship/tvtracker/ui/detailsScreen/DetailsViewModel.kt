package com.summer.internship.tvtracker.ui.detailsScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.summer.internship.tvtracker.data.MoviesRepositoryFactoryIMPL
import com.summer.internship.tvtracker.data.TvDetailsResponse
import com.summer.internship.tvtracker.domain.DetailsResponseListener

class DetailsViewModel() : ViewModel() {
    var id: Long = 0
    private val moviesRepository = MoviesRepositoryFactoryIMPL.createMoviesRepository()
    private val details: MutableLiveData<TvDetailsResponse> by lazy {
        MutableLiveData<TvDetailsResponse>().also {
            loadDetails()
        }
    }

    fun getDetails(): LiveData<TvDetailsResponse> {
        return details
    }

    private fun loadDetails() {
        moviesRepository.getMovieDetails(id, object : DetailsResponseListener {
            override fun onDetailsReceived(detailsResponse: TvDetailsResponse) {
                details.postValue(detailsResponse)
            }

        })
    }
}