package com.summer.internship.tvtracker.data.login

import com.summer.internship.tvtracker.domain.login.SignInDataSource
import com.summer.internship.tvtracker.domain.login.SignInListener
import com.summer.internship.tvtracker.domain.login.SignInRepository

class SignInRepositoryIMPL(private val signInDataSource: SignInDataSource) :
    SignInRepository {
    override fun signIn(email: String, password: String, signInListener: SignInListener) {
        signInDataSource.signIn(email, password, signInListener)
    }
}