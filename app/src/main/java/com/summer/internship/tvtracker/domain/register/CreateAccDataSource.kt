package com.summer.internship.tvtracker.domain.register

import io.reactivex.rxjava3.core.Single

interface CreateAccDataSource {
    fun createAccount(
        email: String,
        password: String,
    ):Single<String>
}