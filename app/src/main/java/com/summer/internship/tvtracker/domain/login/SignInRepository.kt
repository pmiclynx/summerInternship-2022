package com.summer.internship.tvtracker.domain.login

interface SignInRepository {
    fun signIn(email: String, password: String, signInListener: SignInListener)
}