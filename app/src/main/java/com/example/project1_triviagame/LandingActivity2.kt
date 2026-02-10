package com.example.project1_triviagame

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.project1_triviagame.ui.LandingScreen

class TitleActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            LandingScreen(
                onTap = {
                    startActivity(
                        Intent(this, LoginActivity::class.java)
                    )
                }
            )
        }
    }
}
