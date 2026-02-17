package com.example.project1_triviagame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import com.example.project1_triviagame.database.AppDatabase
import com.example.project1_triviagame.database.StatsEntity
import com.example.project1_triviagame.ui.GameScreen
import com.example.project1_triviagame.ui.theme.Project1_TriviaGameTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GameActivityCompose : ComponentActivity() {

    // ✅ prevents double-increment if onGameFinished is called more than once
    @Volatile
    private var resultSaved = false

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

                        // ✅ guard: ignore repeat calls
                        if (resultSaved) return@GameScreen
                        resultSaved = true

                        lifecycleScope.launch {
                            withContext(Dispatchers.IO) {
                                val statsDao = AppDatabase
                                    .getDatabase(applicationContext)
                                    .statsDao()

                                // Ensure the single stats row exists
                                val stats = statsDao.getStats()
                                if (stats == null) {
                                    statsDao.upsert(StatsEntity()) // id=1 wins=0 losses=0
                                }

                                if (isWin) {
                                    statsDao.incrementWins()
                                } else {
                                    statsDao.incrementLosses()
                                }
                            }

                            // Back to Landing (DifficultyActivity already finishes when starting this)
                            finish()
                        }
                    }
                )
            }
        }
    }
}