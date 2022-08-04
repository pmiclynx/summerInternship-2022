package com.summer.internship.tvtracker.login.UI

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.summer.internship.tvtracker.UI.MainActivity
import com.summer.internship.tvtracker.databinding.ActivityLoginBinding
import com.summer.internship.tvtracker.login.Data.SignInFirebaseDataSource
import com.summer.internship.tvtracker.login.Domain.SignInListener
import com.summer.internship.tvtracker.login.Data.SignInRepositoryFactoryIMPL
import com.summer.internship.tvtracker.register.UI.RegisterActivity

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
            val signInRepository = SignInRepositoryFactoryIMPL.createSignInRepository(
                SignInFirebaseDataSource()
            )
            signInRepository.signIn(
                binding.editTextEmail.text.toString(),
                binding.editTextPassword.text.toString(),
                object : SignInListener {
                    override fun onSignIn() {
                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }

                    override fun onError() {
                        Toast.makeText(
                            this@LoginActivity,
                            "Authentication failed",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }

                })
        }
        binding.textViewRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }


}