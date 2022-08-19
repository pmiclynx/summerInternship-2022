package com.summer.internship.tvtracker.domain.login

interface SignInDataSource {
    fun signIn(email: String, password: String, signInListener: SignInListener)
}