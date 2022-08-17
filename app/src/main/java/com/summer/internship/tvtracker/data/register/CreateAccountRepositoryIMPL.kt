package com.summer.internship.tvtracker.data.register

import com.summer.internship.tvtracker.domain.register.CreateAccDataSource
import com.summer.internship.tvtracker.domain.register.CreateAccountListener
import com.summer.internship.tvtracker.domain.register.CreateAccountRepository

class CreateAccountRepositoryIMPL(private val createAccDataSource: CreateAccDataSource) :
    CreateAccountRepository {
    override fun createAccount(
        email: String,
        password: String,
        createAccountListener: CreateAccountListener
    ) {
        createAccDataSource.createAccount(email, password, createAccountListener)
    }
}