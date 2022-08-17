package com.summer.internship.tvtracker.ui.login

import android.util.Patterns
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.summer.internship.tvtracker.data.login.SignInRepositoryFactoryIMPL
import com.summer.internship.tvtracker.domain.login.SignInListener

class LoginViewModel : ViewModel() {
    private val signInRepository = SignInRepositoryFactoryIMPL.createSignInRepository()
    fun isValid(email:String, password:String):Boolean{
        if (email.isEmpty() || password.isEmpty()) {
            return false
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email)
                .matches()
        ) {
            return false
        }
        return true
    }
    fun signIn(email:String, password:String,signInListener: SignInListener){
        signInRepository.signIn(email,password, signInListener)
    }
}