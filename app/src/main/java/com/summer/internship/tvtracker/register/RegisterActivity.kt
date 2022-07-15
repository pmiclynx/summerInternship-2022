package com.summer.internship.tvtracker.register

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.summer.internship.tvtracker.MainActivity
import com.summer.internship.tvtracker.databinding.ActivityRegisterBinding

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
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
            finish()
        }
    }
}