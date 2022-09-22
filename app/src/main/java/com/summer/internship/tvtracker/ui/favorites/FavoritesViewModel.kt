package com.summer.internship.tvtracker.ui.favorites

import com.summer.internship.tvtracker.domain.FavoriteMovieUI
import com.summer.internship.tvtracker.ui.baseMoviesFragment.BaseMoviesViewModel
import io.reactivex.rxjava3.core.Single

class FavoritesViewModel() : BaseMoviesViewModel<FavoriteMovieUI>() {

    fun deleteFavorite(id: Long) {
        (movies.value as ArrayList).removeIf {
            it.id == id
        }
        moviesRepository.deleteFavorite(id)
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    override fun getSingleMovies(): Single<List<FavoriteMovieUI>> {
        return moviesRepository.getFavorites()
    }
}