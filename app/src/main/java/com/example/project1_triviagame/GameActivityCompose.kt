package com.example.project1_triviagame

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.project1_triviagame.ui.GameScreen
import com.example.project1_triviagame.ui.theme.Project1_TriviaGameTheme

class GameActivityCompose : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val difficulty = intent.getStringExtra("difficulty") ?: "easy"
        val categoryId = intent.getIntExtra("category_id", 18)

        setContent {
            Project1_TriviaGameTheme {
                GameScreen(
                    difficulty = difficulty,
                    categoryId = categoryId,
                    onGameFinished = { isWin ->
                        val intent = Intent(this@GameActivityCompose, LandingActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                        startActivity(intent)
                        finish()
                    }
                )
            }
        }
    }
}