package com.summer.internship.tvtracker.ui.detailsScreen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.summer.internship.tvtracker.data.TvDetailsResponse
import com.summer.internship.tvtracker.di.DependencyInjector
import com.summer.internship.tvtracker.domain.DetailsResponseListener
import com.summer.internship.tvtracker.domain.details.OnAddListener
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class DetailsViewModel() : ViewModel() {
    private val moviesRepository = DependencyInjector.provideMovieRepository()
    val compositeDisposable = CompositeDisposable()
    private val _isAddSuccessful: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    val isAddSuccessful: LiveData<Boolean> by lazy {
        _isAddSuccessful
    }

    val isAddFailed: LiveData<String> by lazy {
        _isAddFailed
    }
    private val _isAddFailed: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    private val details: MutableLiveData<TvDetailsResponse> by lazy {
        MutableLiveData<TvDetailsResponse>()
    }

    fun getDetails(): LiveData<TvDetailsResponse> {
        return details
    }

    fun loadDetails(id: Long) {
        moviesRepository.getMovieDetails(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<TvDetailsResponse> {
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onSuccess(t: TvDetailsResponse) {
                    val url = t.backdropPath
                    val posterUrl = t.posterPath
                    t.backdropPath = "https://image.tmdb.org/t/p/w780${url}"
                    t.posterPath = "https://image.tmdb.org/t/p/w500${posterUrl}"
                    details.postValue(t)
                }

                override fun onError(e: Throwable) {
                }

            })
//        moviesRepository.getMovieDetails(id, object : DetailsResponseListener {
//            override fun onDetailsReceived(detailsResponse: TvDetailsResponse) {
//                details.postValue(detailsResponse)
//            }
//
//        })
    }

    fun addFavorite(
        detailsResponse: TvDetailsResponse,
        id: Long?
    ) {
        val disposable = moviesRepository.addFavorite(detailsResponse, id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                _isAddSuccessful.postValue(true)
            }, {
                Log.d("aitag1",it.toString())
                _isAddFailed.postValue(it.toString())
            })
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}