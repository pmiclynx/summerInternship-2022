package com.summer.internship.tvtracker.data.register

import android.util.Log
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.summer.internship.tvtracker.data.login.SignInFirebaseDataSource
import com.summer.internship.tvtracker.domain.register.CreateAccDataSource
import com.summer.internship.tvtracker.domain.register.CreateAccountListener
import io.reactivex.rxjava3.core.Single

class CreateAccountFirebaseDataSource : CreateAccDataSource {

    override fun createAccount(
        email: String,
        password: String,
    ): Single<String> {
        return Single.create { emitter ->
            Firebase.auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success")
                        Firebase.auth.uid?.let {
                            emitter.onSuccess(it)
                        }

                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        emitter.onError(task.exception!!)
                    }
                }
        }
    }

    companion object {
        private const val TAG = "EmailPassword"
    }
}