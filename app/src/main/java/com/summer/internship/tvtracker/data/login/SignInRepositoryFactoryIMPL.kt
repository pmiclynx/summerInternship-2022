package com.summer.internship.tvtracker.data.login

import com.summer.internship.tvtracker.domain.login.SignInRepository
import com.summer.internship.tvtracker.domain.login.SignInRepositoryFactory

object SignInRepositoryFactoryIMPL : SignInRepositoryFactory {
    object Factory : SignInRepositoryFactory {
        override fun createSignInRepository(): SignInRepository {
            return SignInRepositoryIMPL(SignInFirebaseDataSource())
        }

    }

    override fun createSignInRepository(): SignInRepository = Factory.createSignInRepository()
}