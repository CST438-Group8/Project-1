package com.example.project1_triviagame

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.project1_triviagame.ui.DifficultyScreen
import com.example.project1_triviagame.ui.theme.Project1_TriviaGameTheme

class DifficultyActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val categoryId = intent.getIntExtra("category_id", 18)

        setContent {
            Project1_TriviaGameTheme {
                DifficultyScreen(
                    onDifficultySelected = { difficulty ->
                        val intent = Intent(this@DifficultyActivity, GameActivityCompose::class.java)
                        intent.putExtra("difficulty", difficulty)
                        intent.putExtra("category_id", categoryId)
                        startActivity(intent)
                    }
                )
            }
        }
    }
}