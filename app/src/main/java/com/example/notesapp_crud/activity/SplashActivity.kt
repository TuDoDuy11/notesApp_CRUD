package com.example.notesapp_crud.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.notesapp_crud.R

class SplashActivity:AppCompatActivity() {
    private val SPLASH_DELAY: Long = 2000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        // Delay for splash screen
        Handler().postDelayed({
            // Start your main activity
            val intent = Intent(this@SplashActivity, LoginActivity::class.java)
            startActivity(intent)
            finish() // Close splash activity so the user won't come back to it when pressing back button
        }, SPLASH_DELAY)
    }

}