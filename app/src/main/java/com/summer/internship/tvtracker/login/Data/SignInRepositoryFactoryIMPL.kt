package com.summer.internship.tvtracker.login.Data

import com.summer.internship.tvtracker.login.Data.SignInRepositoryIMPL
import com.summer.internship.tvtracker.login.Domain.SignInDataSource
import com.summer.internship.tvtracker.login.Domain.SignInRepository
import com.summer.internship.tvtracker.login.Domain.SignInRepositoryFactory

object SignInRepositoryFactoryIMPL: SignInRepositoryFactory {
    override fun createSignInRepository(signInDataSource: SignInDataSource): SignInRepository {
        return SignInRepositoryIMPL(signInDataSource)
    }
}