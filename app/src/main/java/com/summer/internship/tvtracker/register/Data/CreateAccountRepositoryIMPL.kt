package com.summer.internship.tvtracker.register.Data

import com.summer.internship.tvtracker.register.Domain.CreateAccDataSource
import com.summer.internship.tvtracker.register.Domain.CreateAccountListener
import com.summer.internship.tvtracker.register.Domain.CreateAccountRepository

class CreateAccountRepositoryIMPL(private val createAccountFirebaseDataSource: CreateAccDataSource) :
    CreateAccountRepository {
    override fun createAccount(
        email: String,
        password: String,
        createAccountListener: CreateAccountListener
    ) {
        createAccountFirebaseDataSource.createAccount(email, password, createAccountListener)
    }
}