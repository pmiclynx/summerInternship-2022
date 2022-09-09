package com.summer.internship.tvtracker.data.login

import android.util.Log
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.summer.internship.tvtracker.domain.login.SignInDataSource
import com.summer.internship.tvtracker.domain.login.SignInListener
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

class SignInFirebaseDataSource : SignInDataSource {
    override fun signIn(
        email: String,
        password: String
    ): Single<String> {
        Log.d(TAG, "signIn:$email")
        return Single.create { emitter ->
            Firebase.auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithEmail:success")
                        Firebase.auth.uid?.let {
                            emitter.onSuccess(it)
                        }

//                    signInListener.onSignIn()

                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.exception)
                        emitter.onError(task.exception!!)
//                    signInListener.onError()
                    }
                }
        }


    }

    companion object {
        private const val TAG = "EmailPassword"
    }
}