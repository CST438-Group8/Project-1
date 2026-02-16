package com.example.project1_triviagame

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.example.project1_triviagame.database.AppDatabase
import com.example.project1_triviagame.database.StatsEntity
import com.example.project1_triviagame.ui.LandingScreen
import com.example.project1_triviagame.ui.TRIVIA_CATEGORIES
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LandingActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val context = LocalContext.current

            var wins by remember { mutableIntStateOf(0) }
            var losses by remember { mutableIntStateOf(0) }

            suspend fun loadStats() {
                val result = withContext(Dispatchers.IO) {
                    val statsDao = AppDatabase.getDatabase(applicationContext).statsDao()
                    var stats = statsDao.getStats()
                    if (stats == null) {
                        statsDao.upsert(StatsEntity())
                        stats = statsDao.getStats()
                    }
                    (stats?.wins ?: 0) to (stats?.losses ?: 0)
                }

                wins = result.first
                losses = result.second
            }

            LaunchedEffect(Unit) { loadStats() }

            LandingScreen(
                categories = TRIVIA_CATEGORIES,
                wins = wins,
                losses = losses,
                onPlay = { categoryId ->
                    val intent = Intent(context, DifficultyActivity::class.java)
                    intent.putExtra("category_id", categoryId)
                    context.startActivity(intent)
                },
                onRefreshStats = { loadStats() } // used when you return back
            )
        }
    }
}