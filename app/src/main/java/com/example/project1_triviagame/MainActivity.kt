package com.example.project1_triviagame

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.project1_triviagame.ui.HomeScreen
import com.example.project1_triviagame.ui.theme.Project1_TriviaGameTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Project1_TriviaGameTheme {
                HomeScreen(onPlayClick = {
                    startActivity(Intent(this@MainActivity, LandingActivity::class.java))
                })
            }
        }
    }
}