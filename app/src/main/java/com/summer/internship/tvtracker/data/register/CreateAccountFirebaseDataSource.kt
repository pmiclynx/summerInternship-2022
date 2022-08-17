package com.summer.internship.tvtracker.data.register

import android.util.Log
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.summer.internship.tvtracker.domain.register.CreateAccDataSource
import com.summer.internship.tvtracker.domain.register.CreateAccountListener

class CreateAccountFirebaseDataSource : CreateAccDataSource {

    override fun createAccount(
        email: String,
        password: String,
        createAccountListener: CreateAccountListener
    ) {

        Log.d(TAG, "createAccount:$email")
        Firebase.auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    createAccountListener.onCreateAccount()

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                }
            }
    }

    companion object {
        private const val TAG = "EmailPassword"
    }
}