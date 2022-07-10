package com.summer.internship.tvtracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val email=findViewById<EditText>(R.id.editTextTextPersonName2)
        val password=findViewById<EditText>(R.id.editTextTextPassword)
        val login=findViewById<Button>(R.id.button)

        login.setOnClickListener {
            if(email.text.isEmpty() || password.text.isEmpty()) {
                Toast.makeText(this, "Nu ati introdus email sau parola",Toast.LENGTH_SHORT)
                    .show()
            }
            else
            {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}