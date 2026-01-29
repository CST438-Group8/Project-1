package com.example.project1_triviagame

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val signUpText = findViewById<TextView>(R.id.signUpText)

        signUpText.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
                startActivity(intent)
        }
    }
}