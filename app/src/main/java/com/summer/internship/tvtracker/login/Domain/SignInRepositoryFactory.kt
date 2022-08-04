package com.summer.internship.tvtracker.login.Domain

import com.summer.internship.tvtracker.login.Domain.SignInDataSource
import com.summer.internship.tvtracker.login.Domain.SignInRepository

interface SignInRepositoryFactory {
    fun createSignInRepository(signInDataSource: SignInDataSource): SignInRepository
}