package com.summer.internship.tvtracker.Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.summer.internship.tvtracker.MainActivity
import com.summer.internship.tvtracker.Register.RegisterActivity
import com.summer.internship.tvtracker.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonLogin.setOnClickListener {
            if (binding.editTextEmail.text.isEmpty() || binding.editTextPassword.text.isEmpty()) {
                Toast.makeText(this, "Email or password not entered", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(binding.editTextEmail.getText().toString())
                    .matches()
            ) {
                Toast.makeText(this, "invalid email", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
            signIn(binding.editTextEmail.text.toString(), binding.editTextPassword.text.toString())
        }
        binding.textViewRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun signIn(email: String, password: String) {
        Log.d(TAG, "signIn:$email")
        if (!validateForm()) {
            return
        }
        Firebase.auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(
                        this, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    private fun validateForm(): Boolean {
        var valid = true

        val email = binding.editTextEmail.text.toString()
        if (TextUtils.isEmpty(email)) {
            binding.editTextEmail.error = "Required."
            valid = false
        } else {
            binding.editTextEmail.error = null
        }

        val password = binding.editTextPassword.text.toString()
        if (TextUtils.isEmpty(password)) {
            binding.editTextPassword.error = "Required."
            valid = false
        } else {
            binding.editTextPassword.error = null
        }

        return valid
    }

    companion object {
        private const val TAG = "EmailPassword"
    }
}