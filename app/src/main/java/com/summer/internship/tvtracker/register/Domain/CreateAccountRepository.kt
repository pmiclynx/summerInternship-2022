package com.summer.internship.tvtracker.register.Domain

import com.summer.internship.tvtracker.register.Domain.CreateAccountListener

interface CreateAccountRepository {
    fun createAccount(
        email: String,
        password: String,
        createAccountListener: CreateAccountListener
    )
}