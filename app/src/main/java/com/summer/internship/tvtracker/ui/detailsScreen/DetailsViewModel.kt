package com.summer.internship.tvtracker.ui.detailsScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.summer.internship.tvtracker.data.TvDetailsResponse
import com.summer.internship.tvtracker.di.DependencyInjector
import com.summer.internship.tvtracker.domain.DetailsResponseListener
import com.summer.internship.tvtracker.domain.details.OnAddListener

class DetailsViewModel() : ViewModel() {
    private val moviesRepository = DependencyInjector.provideMovieRepository()
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

    fun addFavorite(
        detailsResponse: TvDetailsResponse,
        id: Long?,
        onAddListener: OnAddListener
    ) {
        moviesRepository.addFavorite(detailsResponse, id, onAddListener)
    }
}