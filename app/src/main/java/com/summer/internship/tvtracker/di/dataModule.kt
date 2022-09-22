package com.summer.internship.tvtracker.di

import com.summer.internship.tvtracker.data.MoviesRepositoryIMPL
import com.summer.internship.tvtracker.domain.MoviesRepository
import org.koin.dsl.module

val dataModule = module {

    single<MoviesRepository> { MoviesRepositoryIMPL(get(), get(), get()) }


}
