package com.summer.internship.tvtracker.data.login

import android.util.Log
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.summer.internship.tvtracker.domain.login.SignInDataSource
import com.summer.internship.tvtracker.domain.login.SignInListener

class SignInFirebaseDataSource: SignInDataSource {
    override fun signIn(email: String, password: String, signInListener: SignInListener) {
        Log.d(TAG, "signIn:$email")
        Firebase.auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    signInListener.onSignIn()

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    signInListener.onError()
                }
            }
    }

    companion object {
        private const val TAG = "EmailPassword"
    }
}