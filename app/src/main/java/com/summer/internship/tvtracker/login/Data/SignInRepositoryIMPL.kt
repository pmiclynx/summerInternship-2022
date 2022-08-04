package com.summer.internship.tvtracker.login.Data

import com.summer.internship.tvtracker.login.Domain.SignInDataSource
import com.summer.internship.tvtracker.login.Domain.SignInListener
import com.summer.internship.tvtracker.login.Domain.SignInRepository

class SignInRepositoryIMPL(private val signInFirebaseDataSource: SignInDataSource) :
    SignInRepository {
    override fun signIn(email: String, password: String, signInListener: SignInListener) {
        signInFirebaseDataSource.signIn(email, password, signInListener)
    }
}