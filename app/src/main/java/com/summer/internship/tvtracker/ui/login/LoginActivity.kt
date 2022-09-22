package com.summer.internship.tvtracker.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.summer.internship.tvtracker.databinding.ActivityLoginBinding
import com.summer.internship.tvtracker.ui.MainActivity
import com.summer.internship.tvtracker.ui.register.RegisterActivity
import io.reactivex.rxjava3.disposables.CompositeDisposable

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    val compositeDisposable = CompositeDisposable()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val model: LoginViewModel by viewModels()
        model.isLoginSuccessful.observe(this) {
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            binding.Loading.visibility = View.GONE
            startActivity(intent)
            finish()
        }
        model.isLoginFailed.observe(this) {
            binding.Loading.visibility = View.GONE
            Toast.makeText(
                this@LoginActivity,
                "Authentication failed",
                Toast.LENGTH_SHORT
            )
                .show()
        }
        binding.buttonLogin.setOnClickListener {

            binding.Loading.visibility = View.VISIBLE
            if (model.isValid(
                    binding.editTextEmail.text.toString(),
                    binding.editTextPassword.text.toString()
                )
            ) {
                model.signIn(
                    binding.editTextEmail.text.toString(),
                    binding.editTextPassword.text.toString(),
                )
            } else {
                binding.Loading.visibility = View.GONE
                Toast.makeText(
                    this@LoginActivity,
                    "Invalid email or password",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }


        }
        binding.textViewRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }


}