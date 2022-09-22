package com.summer.internship.tvtracker.domain

import io.reactivex.rxjava3.core.Single

interface DataSource<FAVORITE> {
    fun getFavorites(): Single<List<FAVORITE>>
    fun deleteFavorite(id: Long)
}