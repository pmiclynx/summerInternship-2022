package com.summer.internship.tvtracker.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import android.os.Handler
import com.summer.internship.tvtracker.ui.MainActivity
import com.summer.internship.tvtracker.R
import com.summer.internship.tvtracker.di.RoomModule11
import com.summer.internship.tvtracker.ui.login.LoginActivity

@SuppressLint("CustomSplashScreen")
@Suppress("DEPRECATION")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        RoomModule11.initApplicationContext {
            applicationContext
        }
        Handler().postDelayed({
            if(Firebase.auth.currentUser != null){
                val intent = Intent(this, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                startActivity(intent)
                finish()
                return@postDelayed
            }
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000)
    }
}