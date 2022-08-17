package com.summer.internship.tvtracker.data.register

import com.summer.internship.tvtracker.domain.register.CreateAccRepositoryFactory
import com.summer.internship.tvtracker.domain.register.CreateAccountRepository

object CreateAccRepositoryFactoryIMPL : CreateAccRepositoryFactory {
    object Factory : CreateAccRepositoryFactory {
        override fun createAccRepository(): CreateAccountRepository {
            return CreateAccountRepositoryIMPL(CreateAccountFirebaseDataSource())
        }

    }

    override fun createAccRepository(): CreateAccountRepository = Factory.createAccRepository()

}