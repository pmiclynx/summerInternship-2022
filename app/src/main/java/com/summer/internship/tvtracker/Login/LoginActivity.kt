package com.summer.internship.tvtracker.Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
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
            if(binding.editTextEmail.text.isEmpty() || binding.editTextPassword.text.isEmpty()) {
                Toast.makeText(this, "Email or password not entered",Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
            if(!Patterns.EMAIL_ADDRESS.matcher(binding.editTextEmail.getText().toString()).matches())
            {
                Toast.makeText(this, "invalid email",Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()

        }
        binding.textViewRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}