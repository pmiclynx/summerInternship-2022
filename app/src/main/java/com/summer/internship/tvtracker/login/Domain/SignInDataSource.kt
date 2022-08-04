package com.summer.internship.tvtracker.login.Domain

interface SignInDataSource {
    fun signIn(email: String, password: String, signInListener: SignInListener)
}