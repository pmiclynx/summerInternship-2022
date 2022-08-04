package com.summer.internship.tvtracker.register.UI

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.summer.internship.tvtracker.UI.MainActivity
import com.summer.internship.tvtracker.databinding.ActivityRegisterBinding
import com.summer.internship.tvtracker.register.Data.CreateAccRepositoryFactoryIMPL
import com.summer.internship.tvtracker.register.Data.CreateAccountFirebaseDataSource
import com.summer.internship.tvtracker.register.Domain.CreateAccountListener

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonRegister.setOnClickListener {
            if (binding.editTextEmail.text.isEmpty() || binding.editTextPassword.text.isEmpty() || binding.editTextName.text.isEmpty() || binding.editTextVerifyPassword.text.isEmpty()) {
                Toast.makeText(this, "All fields must be filled", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
            val pass: String = binding.editTextPassword.text.toString()
            val verifyPass: String = binding.editTextVerifyPassword.text.toString()
            if (!pass.equals(verifyPass)) {
                Toast.makeText(
                    this,
                    "Passwords doesn't match",
                    Toast.LENGTH_SHORT
                )
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
            if (pass.length < 6) {
                Toast.makeText(this, "Password must be at least 6 characters", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
            val createAccountRepository = CreateAccRepositoryFactoryIMPL.createAccRepository(
                CreateAccountFirebaseDataSource()
            )
            createAccountRepository.createAccount(
                binding.editTextEmail.text.toString(),
                binding.editTextPassword.text.toString(),
                object : CreateAccountListener {
                    override fun onCreateAccount() {
                        val intent = Intent(this@RegisterActivity, MainActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        startActivity(intent)
                        finish()
                    }

                    override fun onError() {
                        Toast.makeText(
                            this@RegisterActivity,
                            "Error creating account",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }

                }
            )
        }
    }


}