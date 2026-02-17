package com.example.project1_triviagame

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.example.project1_triviagame.database.AppDatabase
import com.example.project1_triviagame.database.StatsEntity
import com.example.project1_triviagame.ui.LandingScreen
import com.example.project1_triviagame.ui.TRIVIA_CATEGORIES
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LandingActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val context = LocalContext.current
            val lifecycleOwner = LocalLifecycleOwner.current
            val scope = rememberCoroutineScope()

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

            // Initial load
            LaunchedEffect(Unit) { loadStats() }

            // ✅ Critical fix: reload stats every time this screen comes back to the front
            DisposableEffect(lifecycleOwner) {
                val observer = LifecycleEventObserver { _, event ->
                    if (event == Lifecycle.Event.ON_RESUME) {
                        scope.launch { loadStats() }
                    }
                }
                lifecycleOwner.lifecycle.addObserver(observer)
                onDispose { lifecycleOwner.lifecycle.removeObserver(observer) }
            }

            LandingScreen(
                categories = TRIVIA_CATEGORIES,
                wins = wins,
                losses = losses,
                onPlay = { categoryId ->
                    val intent = Intent(context, DifficultyActivity::class.java)
                    intent.putExtra("category_id", categoryId)
                    context.startActivity(intent)
                },
                onRefreshStats = { loadStats() },
                onLogout = {
                    val intent = Intent(context, LoginActivity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    }
                    context.startActivity(intent)
                }
            )
        }
    }
}