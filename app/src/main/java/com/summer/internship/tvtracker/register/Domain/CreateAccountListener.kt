package com.summer.internship.tvtracker.register.Domain

interface CreateAccountListener {
    fun onCreateAccount()
    fun onError()
}