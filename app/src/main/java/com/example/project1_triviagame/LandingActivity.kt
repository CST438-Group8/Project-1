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
import com.example.project1_triviagame.ui.HangmanCarouselAdapter
import com.example.project1_triviagame.ui.HangmanThemeOption
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class LandingActivity : AppCompatActivity() {

    private lateinit var recycler: RecyclerView
    private lateinit var btnLeft: View
    private lateinit var btnRight: View
    private lateinit var btnPlay: MaterialButton

    private lateinit var tvWins: android.widget.TextView
    private lateinit var tvLosses: android.widget.TextView

    private val options = listOf(
        HangmanThemeOption(id = 0, textColorHex = "#00E5FF"), // cyan
        HangmanThemeOption(id = 1, textColorHex = "#FFEA00"), // yellow
        HangmanThemeOption(id = 2, textColorHex = "#FF4DFF")  // pink
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)

        recycler = findViewById(R.id.hangmanCarousel)
        btnLeft = findViewById(R.id.btnLeft)
        btnRight = findViewById(R.id.btnRight)
        btnPlay = findViewById(R.id.btnPlay)
        tvWins = findViewById(R.id.tvWins)
        tvLosses = findViewById(R.id.tvLosses)

        // Carousel setup
        recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recycler.adapter = HangmanCarouselAdapter(options)
        recycler.setHasFixedSize(true)

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(recycler)

        // Start centered on item 1 (optional)
        recycler.scrollToPosition(1)

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

        // Play -> DifficultyScreen
        btnPlay.setOnClickListener {
            val selected = currentPosition().coerceIn(0, options.lastIndex)

            // Launch Difficulty screen without a compile-time class reference.
            // If your class name is different, update the string below.
            val intent = Intent().setClassName(this, "${packageName}.DifficultyScreen")
            intent.putExtra("theme_id", options[selected].id)
            startActivity(intent)
        }

        // Room counters


        // Add some glow-ish elevation to Play button
        btnPlay.elevation = 18f
    }
}