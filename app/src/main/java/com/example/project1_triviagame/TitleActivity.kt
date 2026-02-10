package com.example.project1_triviagame

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.project1_triviagame.ui.TitleScreen

class TitleActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TitleScreen(
                onTap = {
                    startActivity(
                        Intent(this, LoginActivity::class.java)
                    )
                }
            )
        }
    }
}
