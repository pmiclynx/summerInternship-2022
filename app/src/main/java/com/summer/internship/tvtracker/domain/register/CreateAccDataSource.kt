package com.summer.internship.tvtracker.domain.register

interface CreateAccDataSource {
    fun createAccount(
        email: String,
        password: String,
        createAccountListener: CreateAccountListener
    )
}