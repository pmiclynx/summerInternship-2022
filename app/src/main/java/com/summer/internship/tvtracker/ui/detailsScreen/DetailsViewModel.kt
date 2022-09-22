package com.summer.internship.tvtracker.ui.detailsScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.summer.internship.tvtracker.data.TvDetailsUi
import com.summer.internship.tvtracker.domain.MoviesRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DetailsViewModel() : ViewModel(), KoinComponent {
    val compositeDisposable = CompositeDisposable()
    private val moviesRepository: MoviesRepository by inject()
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
    private val details: MutableLiveData<TvDetailsUi> by lazy {
        MutableLiveData<TvDetailsUi>()
    }

    fun getDetails(): LiveData<TvDetailsUi> {
        return details
    }

    fun loadDetails(id: Long) {
        moviesRepository.getMovieDetails(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<TvDetailsUi> {
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onSuccess(t: TvDetailsUi) {
                    details.postValue(t)
                }

                override fun onError(e: Throwable) {
                }

            })
    }

    fun addFavorite(
        detailsResponse: TvDetailsUi,
        id: Long?
    ) {
        val disposable = moviesRepository.addFavorite(detailsResponse, id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                _isAddSuccessful.postValue(true)
            }, {
                _isAddFailed.postValue(it.toString())
            })
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}