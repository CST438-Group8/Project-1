package com.example.project1_triviagame

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.project1_triviagame.ui.GameScreen
import com.example.project1_triviagame.ui.theme.Project1_TriviaGameTheme
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import com.example.project1_triviagame.database.AppDatabase
import com.example.project1_triviagame.database.StatsEntity

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

                        // Save result to Room
                        lifecycleScope.launch {
                            val statsDao = AppDatabase
                                .getDatabase(applicationContext)
                                .statsDao()

                            val stats = statsDao.getStats()

                            if (stats == null) {
                                // First time creation safety
                                statsDao.upsert(StatsEntity())
                            }

                            if (isWin) {
                                statsDao.incrementWins()
                            } else {
                                statsDao.incrementLosses()
                            }

                            finish()
                        }
                    }
                )
            }
        }
    }
}
