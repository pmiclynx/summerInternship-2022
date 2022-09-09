package com.summer.internship.tvtracker.domain.login

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface SignInDataSource {
    fun signIn(email: String, password: String): Single<String>
}