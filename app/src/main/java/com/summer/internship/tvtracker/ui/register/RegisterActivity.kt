package com.summer.internship.tvtracker.ui.register

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.summer.internship.tvtracker.databinding.ActivityRegisterBinding
import com.summer.internship.tvtracker.ui.MainActivity
import com.summer.internship.tvtracker.domain.register.CreateAccountListener

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val model: RegisterViewModel by viewModels()
        model.isLoginSuccessful.observe(this) {
            val intent = Intent(this@RegisterActivity, MainActivity::class.java)
            binding.Loading.visibility = View.GONE
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
            finish()
        }
        model.isLoginFailed.observe(this) {
            binding.Loading.visibility = View.GONE
            Toast.makeText(
                this@RegisterActivity,
                "Error creating account",
                Toast.LENGTH_SHORT
            )
                .show()
        }
        binding.buttonRegister.setOnClickListener {
            binding.Loading.visibility = View.VISIBLE
            if (model.isOk(
                    binding.editTextEmail.text.toString(),
                    binding.editTextPassword.text.toString(),
                    binding.editTextName.text.toString(),
                    binding.editTextVerifyPassword.text.toString()
                )
            ) {
                model.createAccount(
                    binding.editTextEmail.text.toString(),
                    binding.editTextPassword.text.toString()
                )
            } else {
                binding.Loading.visibility = View.GONE
                Toast.makeText(
                    this@RegisterActivity,
                    "Invalid fields",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }

        }
    }


}