package com.summer.internship.tvtracker.domain.login

interface SignInRepositoryFactory {
    fun createSignInRepository(): SignInRepository
}