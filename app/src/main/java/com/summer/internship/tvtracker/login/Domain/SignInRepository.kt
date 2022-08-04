package com.summer.internship.tvtracker.login.Domain

import com.summer.internship.tvtracker.login.Domain.SignInListener

interface SignInRepository {
    fun signIn(email: String, password: String, signInListener: SignInListener)
}