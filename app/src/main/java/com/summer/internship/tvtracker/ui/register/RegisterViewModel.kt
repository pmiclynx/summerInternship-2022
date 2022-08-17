package com.summer.internship.tvtracker.ui.register

import android.util.Patterns
import androidx.lifecycle.ViewModel
import com.summer.internship.tvtracker.data.register.CreateAccRepositoryFactoryIMPL
import com.summer.internship.tvtracker.domain.register.CreateAccountListener

class RegisterViewModel : ViewModel() {

    private val createAccountRepository = CreateAccRepositoryFactoryIMPL.createAccRepository()

    fun isOk(email: String, password: String, name: String, verifyPass: String): Boolean {
        if (email.isEmpty() || password.isEmpty() || name.isEmpty() || verifyPass.isEmpty()) {
            return false
        }
        if (!password.equals(verifyPass)) {
            return false
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email)
                .matches()
        ) {
            return false
        }
        if (password.length < 6) {
            return false
        }
        return true
    }

    fun createAccount(email: String,password: String,createAccountListener: CreateAccountListener){
        createAccountRepository.createAccount(email, password, createAccountListener)
    }
}