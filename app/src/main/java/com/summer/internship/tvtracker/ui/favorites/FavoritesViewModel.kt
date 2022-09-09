package com.summer.internship.tvtracker.ui.favorites

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.summer.internship.tvtracker.data.TvDetailsResponse
import com.summer.internship.tvtracker.di.DependencyInjector
import com.summer.internship.tvtracker.domain.FavoriteMovie
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class FavoritesViewModel() : ViewModel() {
    private val moviesRepository = DependencyInjector.provideMovieRepository()
    val compositeDisposable = CompositeDisposable()

    private val favoriteMovies: MutableLiveData<List<FavoriteMovie>> by lazy {
        MutableLiveData<List<FavoriteMovie>>()
    }

    fun getFavoriteMovies(): LiveData<List<FavoriteMovie>> {
        return favoriteMovies
    }

    fun loadFavoriteMovies() {
        moviesRepository.getFavorites()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<List<FavoriteMovie>> {
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onSuccess(t: List<FavoriteMovie>) {
                    favoriteMovies.postValue(t)
                }

                override fun onError(e: Throwable) {

                }

            })
    }

    fun deleteFavorite(id: String) {
        (favoriteMovies.value as ArrayList).removeIf {
            it.id === id
        }
        moviesRepository.deleteFavorite(id)
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}