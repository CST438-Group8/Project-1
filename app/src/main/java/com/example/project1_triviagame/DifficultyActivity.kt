package com.example.project1_triviagame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.project1_triviagame.ui.DifficultyScreen
import com.example.project1_triviagame.ui.theme.Project1_TriviaGameTheme

class DifficultyActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Project1_TriviaGameTheme {
                DifficultyScreen()
            }
        }
    }
}
