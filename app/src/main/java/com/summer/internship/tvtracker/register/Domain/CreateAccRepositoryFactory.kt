package com.summer.internship.tvtracker.register.Domain

import com.summer.internship.tvtracker.register.Domain.CreateAccDataSource
import com.summer.internship.tvtracker.register.Domain.CreateAccountRepository

interface CreateAccRepositoryFactory {
    fun createAccRepository(createAccDataSource: CreateAccDataSource): CreateAccountRepository
}