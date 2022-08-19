package com.summer.internship.tvtracker.domain

interface MoviesRepositoryFactory {
    fun createMoviesRepository(): MoviesRepository
}