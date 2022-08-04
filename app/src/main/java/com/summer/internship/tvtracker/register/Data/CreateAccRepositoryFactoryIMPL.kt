package com.summer.internship.tvtracker.register.Data

import com.summer.internship.tvtracker.register.Domain.CreateAccDataSource
import com.summer.internship.tvtracker.register.Domain.CreateAccRepositoryFactory
import com.summer.internship.tvtracker.register.Domain.CreateAccountRepository

object CreateAccRepositoryFactoryIMPL: CreateAccRepositoryFactory {
    override fun createAccRepository(createAccDataSource: CreateAccDataSource): CreateAccountRepository {
        return CreateAccountRepositoryIMPL(createAccDataSource)
    }
}