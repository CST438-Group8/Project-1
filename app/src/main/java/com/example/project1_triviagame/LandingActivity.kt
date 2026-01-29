package com.example.project1_triviagame

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent

class LandingActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)

        findViewById<android.view.View>(android.R.id.content).setOnClickListener {
            val intent = android.content.Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}