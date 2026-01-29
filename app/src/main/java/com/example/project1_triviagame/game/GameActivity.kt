package com.example.project1_triviagame.game

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.project1_triviagame.R

class GameActivity : AppCompatActivity() {

    private lateinit var questionProgress: ProgressBar
    private lateinit var questionCounter: TextView

    private lateinit var hangmanProgress: ProgressBar
    private lateinit var mistakesText: TextView
    private lateinit var hangmanImage: ImageView

    private lateinit var questionText: TextView
    private lateinit var answerButtons: List<Button>

    // TEMP UI state (replace later with API + real logic)
    private var answeredCount = 0      // 0..10
    private var mistakes = 0           // 0..6

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        questionProgress = findViewById(R.id.questionProgress)
        questionCounter = findViewById(R.id.questionCounter)

        hangmanProgress = findViewById(R.id.hangmanProgress)
        mistakesText = findViewById(R.id.mistakesText)
        hangmanImage = findViewById(R.id.hangmanImage)

        questionText = findViewById(R.id.questionText)

        val a = findViewById<Button>(R.id.answerA)
        val b = findViewById<Button>(R.id.answerB)
        val c = findViewById<Button>(R.id.answerC)
        val d = findViewById<Button>(R.id.answerD)
        answerButtons = listOf(a, b, c, d)

        // Setup bars
        questionProgress.max = 10
        hangmanProgress.max = 6

        // Placeholder UI (API will overwrite these later)
        questionText.text = "Question will load from API..."
        answerButtons.forEach { it.text = "Answer" }

        answerButtons.forEach { btn ->
            btn.setOnClickListener {
                // TEMP behavior:
                // - every click counts as "answered a question"
                // - every other click counts as a "mistake" to demo hangman changing
                answeredCount = (answeredCount + 1).coerceAtMost(10)
                if (answeredCount % 2 == 0) mistakes = (mistakes + 1).coerceAtMost(6)
                render()
            }
        }

        render()
    }

    private fun render() {
        // Top progress
        questionProgress.progress = answeredCount
        questionCounter.text = "Progress: $answeredCount/10"

        // Hangman placeholder progress
        hangmanProgress.progress = mistakes
        mistakesText.text = "Mistakes: $mistakes/6"

        // Optional: swap hangman images if you add hangman_0..hangman_6 drawables
        val stage = mistakes.coerceIn(0, 6)
        val resId = resources.getIdentifier("hangman_$stage", "drawable", packageName)
        if (resId != 0) {
            hangmanImage.setImageResource(resId)
        } else {
            // If you haven't added images yet, just keep whatever placeholder is set in XML
        }

        // Disable answers if finished (optional)
        val gameOver = answeredCount >= 10 || mistakes >= 6
        if (gameOver) {
            answerButtons.forEach { it.isEnabled = false }
            questionText.text = if (mistakes >= 6) "Game Over (placeholder)" else "Finished (placeholder)"
        }
    }
}