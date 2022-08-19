package com.summer.internship.tvtracker.domain.register

interface CreateAccountRepository {
    fun createAccount(
        email: String,
        password: String,
        createAccountListener: CreateAccountListener
    )
}