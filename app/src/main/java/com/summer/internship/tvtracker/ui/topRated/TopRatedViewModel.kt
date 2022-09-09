package com.summer.internship.tvtracker.ui.topRated

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.summer.internship.tvtracker.data.MoviesRepositoryFactoryIMPL
import com.summer.internship.tvtracker.di.DependencyInjector
import com.summer.internship.tvtracker.domain.Movie
import com.summer.internship.tvtracker.domain.MovieResponseListener
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class TopRatedViewModel : ViewModel() {
    val compositeDisposable= CompositeDisposable()
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
        moviesRepository.getTopRated()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object: SingleObserver<List<Movie>> {
                override fun onSubscribe(d: Disposable) {
//                    compositeDisposable.clear()
                    compositeDisposable.add(d)
                }

                override fun onSuccess(t: List<Movie>) {
                    movies.postValue(t)
                    Log.d("airb",Thread.currentThread().toString())
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