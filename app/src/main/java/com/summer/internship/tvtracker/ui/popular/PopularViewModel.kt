package com.summer.internship.tvtracker.ui.popular

import com.summer.internship.tvtracker.domain.MovieUI
import com.summer.internship.tvtracker.ui.baseMoviesFragment.BaseMoviesViewModel
import io.reactivex.rxjava3.core.Single

class PopularViewModel : BaseMoviesViewModel<MovieUI>() {
    override fun getSingleMovies(): Single<List<MovieUI>> {
        return moviesRepository.getPopular()
    }

}