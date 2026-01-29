package com.example.project1_triviagame.data

data class TriviaProgress(
    val totalQuestions: Int = 0,
    val answeredQuestions: Int = 0,
    val correctAnswers: Int = 0,
    val currentQuestionIndex: Int = 0,
    val isCompleted: Boolean = false
)

enum class HangmanStatus {
    IN_PROGRESS,
    WON,
    LOST
}

data class HangmanProgress(
    val targetWord: String = "",
    val revealedLetters: Set<Char> = emptySet(),
    val incorrectGuesses: Set<Char> = emptySet(),
    val maxAttempts: Int = 6,
    val status: HangmanStatus = HangmanStatus.IN_PROGRESS
) {
    val remainingAttempts: Int
        get() = maxAttempts - incorrectGuesses.size
}

data class UserProgress(
    val triviaProgress: TriviaProgress = TriviaProgress(),
    val hangmanProgress: HangmanProgress = HangmanProgress()
)
