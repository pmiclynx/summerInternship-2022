package com.summer.internship.tvtracker.domain.login

import io.reactivex.rxjava3.core.Single

interface SignInRepository {
    fun signIn(email: String, password: String): Single<String>
}