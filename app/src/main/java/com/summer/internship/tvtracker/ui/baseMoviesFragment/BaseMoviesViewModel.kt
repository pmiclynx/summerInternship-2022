package com.summer.internship.tvtracker.ui.baseMoviesFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.summer.internship.tvtracker.domain.MoviesRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

abstract class BaseMoviesViewModel<T> : ViewModel(), KoinComponent {
    protected val compositeDisposable = CompositeDisposable()
    protected val moviesRepository: MoviesRepository by inject()
    protected val movies: MutableLiveData<List<T>> by lazy {
        MutableLiveData<List<T>>().also {
            loadMovies()
        }
    }

    fun getMovies(): LiveData<List<T>> {
        return movies
    }

    protected abstract fun getSingleMovies(): Single<List<T>>

    fun loadMovies() {

        getSingleMovies()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : SingleObserver<List<T>> {
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onSuccess(t: List<T>) {
                    movies.postValue(t)
                }

                override fun onError(e: Throwable) {

                }

            })
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}