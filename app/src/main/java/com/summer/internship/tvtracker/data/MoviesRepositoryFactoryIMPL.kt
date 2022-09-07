package com.summer.internship.tvtracker.data

import com.summer.internship.tvtracker.domain.MoviesRepository
import com.summer.internship.tvtracker.domain.MoviesRepositoryFactory


object MoviesRepositoryFactoryIMPL {

    //    private val instance by lazy { MoviesRepositoryIMPL(MoviesRemoteDataSource) }
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

//    @Volatile
//    private var instance: MoviesRepository? = null
//
//    @Synchronized
//    override fun createMoviesRepository(): MoviesRepository {
//        if (instance == null) {
//            synchronized(this) {
//                if (instance === null) {
//                    instance = MoviesRepositoryIMPL(MoviesRemoteDataSource,MoviesLocalDataSource)
//                }
//            }
//        }
//        return instance as MoviesRepositoryIMPL

        //??
//    override fun createMoviesRepository(): MoviesRepository = instance
//    }
}