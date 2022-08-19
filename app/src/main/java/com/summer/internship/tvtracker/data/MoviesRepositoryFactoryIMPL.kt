package com.summer.internship.tvtracker.data

import com.summer.internship.tvtracker.domain.MoviesRepository
import com.summer.internship.tvtracker.domain.MoviesRepositoryFactory


object MoviesRepositoryFactoryIMPL : MoviesRepositoryFactory {

    private val instance by lazy { MoviesRepositoryIMPL(MoviesRemoteDataSource) }
//        override fun createMoviesRepository(): MoviesRepository {
//            return instance
//        }
    //2
//        private var bool=true
//        private lateinit var inst:MoviesRepository
//        override fun createMoviesRepository(): MoviesRepository {
//            if(bool){
//                bool=false
//                inst=MoviesRepositoryIMPL(MoviesRemoteDataSource)
//                return inst
//            }
//            return inst
//        }

//        private var instance :MoviesRepository? = null
//        override fun createMoviesRepository(): MoviesRepository {
//            if (instance === null) {
//                instance = MoviesRepositoryIMPL(MoviesRemoteDataSource)
//            }
//            return instance as MoviesRepositoryIMPL
//        }

    //??
    override fun createMoviesRepository(): MoviesRepository = instance
}