package com.summer.internship.tvtracker.domain

import com.summer.internship.tvtracker.data.TvDetailsUi
import io.reactivex.rxjava3.core.Single

interface FavoritesDataSource : DataSource<FavoriteMovieUI> {
    fun addFavorite(detailsResponse: TvDetailsUi, id: Long?): Single<String>
}