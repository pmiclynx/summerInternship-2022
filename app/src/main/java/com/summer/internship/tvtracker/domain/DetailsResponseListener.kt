package com.summer.internship.tvtracker.domain

import com.summer.internship.tvtracker.data.TvDetailsResponse

interface DetailsResponseListener {
    fun onDetailsReceived(detailsResponse: TvDetailsResponse)
    fun onError (){

    }
}