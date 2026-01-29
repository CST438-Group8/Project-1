package com.example.project1_triviagame.data

/**
 * Represents the user's overall progress in the trivia portion of the game.
 * @property totalQuestions Total number of trivia questions in the current session.
 * @property answeredQuestions Number of questions the user has attempted.
 * @property correctAnswers Number of questions the user has answered correctly.
 * @property currentQuestionIndex Zero-based index of the question currently being shown.
 * @property isCompleted Indicates whether the trivia session has been completed.
 */
data class TriviaProgress(
    val totalQuestions: Int = 0,
    val answeredQuestions: Int = 0,
    val correctAnswers: Int = 0,
    val currentQuestionIndex: Int = 0,
    val isCompleted: Boolean = false
)

/**
 * Describes the overall outcome or current state of a hangman game.
 */
enum class HangmanStatus {
    IN_PROGRESS,
    WON,
    LOST
}

/**
 * Tracks the user's progress in a single hangman round.
 * @property targetWord The word the user is trying to guess.
 * @property revealedLetters Letters that have been correctly guessed and revealed in the word.
 * @property incorrectGuesses Letters guessed by the user that are not in the word.
 * @property maxAttempts Maximum number of incorrect guesses allowed before the game is lost.
 * @property status Current status of the hangman game (in progress, won, or lost).
 */
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

/**
 * Aggregates the user's progress across all supported game modes.
 *
 * This is intended to be the single source of truth for a user's current
 * session, combining both trivia statistics and hangman state so that
 * progress can be saved and restored consistently.
 * @property triviaProgress The user's current trivia-game progress.
 * @property hangmanProgress The user's current hangman-game progress.
 */
data class UserProgress(
    val triviaProgress: TriviaProgress = TriviaProgress(),
    val hangmanProgress: HangmanProgress = HangmanProgress()
)
