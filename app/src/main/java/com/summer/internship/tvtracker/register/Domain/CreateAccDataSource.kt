package com.summer.internship.tvtracker.register.Domain

interface CreateAccDataSource {
    fun createAccount(
        email: String,
        password: String,
        createAccountListener: CreateAccountListener
    )
}