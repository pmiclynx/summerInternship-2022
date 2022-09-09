package com.summer.internship.tvtracker.data.login

import com.summer.internship.tvtracker.domain.login.SignInDataSource
import com.summer.internship.tvtracker.domain.login.SignInListener
import com.summer.internship.tvtracker.domain.login.SignInRepository
import io.reactivex.rxjava3.core.Single

class SignInRepositoryIMPL(private val signInDataSource: SignInDataSource) :
    SignInRepository {
    override fun signIn(email: String, password: String) :Single<String> {
       return signInDataSource.signIn(email, password)
    }
}