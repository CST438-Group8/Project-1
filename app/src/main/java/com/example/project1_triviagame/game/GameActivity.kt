package com.example.project1_triviagame.game

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.project1_triviagame.R

/**
 * GameActivity
 *
 * This Activity represents the main gameplay screen for the trivia + hangman game.
 * It currently uses placeholder data and logic to demonstrate UI behavior.
 * Real questions and answers will be loaded later from an API.
 */
class GameActivity : AppCompatActivity() {

    // --- Top progress UI (questions answered) ---
    private lateinit var questionProgress: ProgressBar
    private lateinit var questionCounter: TextView

    // --- Hangman UI (mistakes made) ---
    private lateinit var hangmanProgress: ProgressBar
    private lateinit var mistakesText: TextView
    private lateinit var hangmanImage: ImageView

    // --- Question + answers ---
    private lateinit var questionText: TextView
    private lateinit var answerButtons: List<Button>

    // TEMP UI state
    // answeredCount tracks how many questions have been answered (max 10)
    // mistakes tracks how many incorrect answers were made (max 6)
    private var answeredCount = 0
    private var mistakes = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Load the layout for this screen
        setContentView(R.layout.activity_game)

        // --- Find and bind UI elements ---
        questionProgress = findViewById(R.id.questionProgress)
        questionCounter = findViewById(R.id.questionCounter)

        hangmanProgress = findViewById(R.id.hangmanProgress)
        mistakesText = findViewById(R.id.mistakesText)
        hangmanImage = findViewById(R.id.hangmanImage)

        questionText = findViewById(R.id.questionText)

        // Find the four answer buttons
        val a = findViewById<Button>(R.id.answerA)
        val b = findViewById<Button>(R.id.answerB)
        val c = findViewById<Button>(R.id.answerC)
        val d = findViewById<Button>(R.id.answerD)

        // Store answer buttons in a list for easier iteration
        answerButtons = listOf(a, b, c, d)

        // --- Configure progress bars ---
        questionProgress.max = 10     // total questions
        hangmanProgress.max = 6       // total allowed mistakes

        // --- Placeholder UI text ---
        // These values will be replaced later when API data is added
        questionText.text = "Question will load from API..."
        answerButtons.forEach { it.text = "Answer" }

        // --- Temporary click behavior ---
        // Each button click simulates answering a question
        // Every second answer counts as a mistake to demo hangman behavior
        answerButtons.forEach { btn ->
            btn.setOnClickListener {
                answeredCount = (answeredCount + 1).coerceAtMost(10)

                // Add a mistake every other answer (placeholder logic)
                if (answeredCount % 2 == 0) {
                    mistakes = (mistakes + 1).coerceAtMost(6)
                }

                // Update the UI after every click
                render()
            }
        }

        // Initial UI render
        render()
    }

    /**
     * Updates all UI elements based on the current game state.
     * This method keeps UI logic in one place.
     */
    private fun render() {

        // --- Question progress ---
        questionProgress.progress = answeredCount
        questionCounter.text = "Progress: $answeredCount/10"

        // --- Hangman progress ---
        hangmanProgress.progress = mistakes
        mistakesText.text = "Mistakes: $mistakes/6"

        // --- Hangman image update ---
        // Tries to load hangman_0 through hangman_6 from drawable resources
        val stage = mistakes.coerceIn(0, 6)
        val resId = resources.getIdentifier(
            "hangman_$stage",
            "drawable",
            packageName
        )

        // Only update image if drawable exists
        if (resId != 0) {
            hangmanImage.setImageResource(resId)
        }

        // --- Game over handling ---
        val gameOver = answeredCount >= 10 || mistakes >= 6

        if (gameOver) {
            // Disable answer buttons when game ends
            answerButtons.forEach { it.isEnabled = false }

            // Show end-game message
            questionText.text =
                if (mistakes >= 6) "Game Over (placeholder)"
                else "Finished (placeholder)"
        }
    }
}