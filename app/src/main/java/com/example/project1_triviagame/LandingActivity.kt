package com.example.project1_triviagame

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.project1_triviagame.database.AppDatabase
import com.example.project1_triviagame.database.StatsEntity
import com.example.project1_triviagame.ui.HangmanCarouselAdapter
import com.example.project1_triviagame.ui.HangmanThemeOption
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import com.example.project1_triviagame.ui.TRIVIA_CATEGORIES

class LandingActivity : AppCompatActivity() {

    private lateinit var recycler: RecyclerView
    private lateinit var btnLeft: View
    private lateinit var btnRight: View
    private lateinit var btnPlay: MaterialButton

    private lateinit var tvWins: android.widget.TextView
    private lateinit var tvLosses: android.widget.TextView

    private val options = TRIVIA_CATEGORIES.map {
        HangmanThemeOption(
            id = it.id,
            label = it.name,
            textColorHex = "#00E5FF"
        )
    }

    override fun onResume() {
        super.onResume()

        lifecycleScope.launch {
            val statsDao = AppDatabase.getDatabase(applicationContext).statsDao()
            val stats = statsDao.getStats()

            tvWins.text = "Wins: ${stats?.wins ?: 0}"
            tvLosses.text = "Losses: ${stats?.losses ?: 0}"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)

        lifecycleScope.launch {
            val statsDao = AppDatabase.getDatabase(applicationContext).statsDao()

            var stats = statsDao.getStats()

            if (stats == null) {
                statsDao.upsert(StatsEntity())
                stats = statsDao.getStats()
            }

            tvWins.text = "Wins: ${stats?.wins ?: 0}"
            tvLosses.text = "Losses: ${stats?.losses ?: 0}"
        }


        recycler = findViewById(R.id.hangmanCarousel)
        btnLeft = findViewById(R.id.btnLeft)
        btnRight = findViewById(R.id.btnRight)
        btnPlay = findViewById(R.id.btnPlay)
        tvWins = findViewById(R.id.tvWins)
        tvLosses = findViewById(R.id.tvLosses)

        recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recycler.adapter = HangmanCarouselAdapter(options)
        recycler.setHasFixedSize(true)

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(recycler)

        recycler.scrollToPosition(0)

        fun currentPosition(): Int {
            val lm = recycler.layoutManager as LinearLayoutManager
            return lm.findFirstVisibleItemPosition().coerceAtLeast(0)
        }

        btnLeft.setOnClickListener {
            val pos = currentPosition()
            recycler.smoothScrollToPosition((pos - 1).coerceAtLeast(0))
        }

        btnRight.setOnClickListener {
            val pos = currentPosition()
            recycler.smoothScrollToPosition((pos + 1).coerceAtMost(options.lastIndex))
        }

        btnPlay.setOnClickListener {
            val selected = currentPosition().coerceIn(0, options.lastIndex)
            val categoryId = options[selected].id

            val intent = Intent(this, DifficultyActivity::class.java)
            intent.putExtra("category_id", categoryId)
            startActivity(intent)
        }

        btnPlay.elevation = 18f
    }
}