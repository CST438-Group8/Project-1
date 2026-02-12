package com.example.project1_triviagame.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project1_triviagame.network.TriviaApi
import com.example.project1_triviagame.network.TriviaQuestion
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class GameUiState(
    val questions: List<TriviaQuestion> = emptyList(),
    val currentQuestionIndex: Int = 0,
    val answeredCount: Int = 0,
    val mistakes: Int = 0,
    val isLoading: Boolean = true,
    val error: String? = null,
    val hasAnswered: Boolean = false,
    val selectedAnswerIndex: Int? = null
) {
    val currentQuestion: TriviaQuestion?
        get() = questions.getOrNull(currentQuestionIndex)

    val gameOver: Boolean
        get() = answeredCount >= 10 || mistakes >= 6

    val isWin: Boolean
        get() = answeredCount >= 10 && mistakes < 6

    val isLoss: Boolean
        get() = mistakes >= 6
}

class GameViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    fun loadQuestions(difficulty: String = "easy", categoryId: Int = 18) {
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(isLoading = true, error = null)

                val questions = TriviaApi.fetchQuestions(
                    categoryId = categoryId,
                    difficulty = difficulty,
                    amount = 10
                )

                if (questions.isNotEmpty()) {
                    _uiState.value = _uiState.value.copy(
                        questions = questions,
                        isLoading = false
                    )
                } else {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = "Failed to load questions. Please try again."
                    )
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = "Error: ${e.message}"
                )
            }
        }
    }

    fun answerQuestion(selectedIndex: Int) {
        val currentState = _uiState.value
        if (currentState.hasAnswered || currentState.gameOver) return

        val currentQuestion = currentState.currentQuestion ?: return
        val selectedAnswer = currentQuestion.answers.getOrNull(selectedIndex) ?: return
        val isCorrect = selectedAnswer == currentQuestion.correctAnswer

        _uiState.value = currentState.copy(
            hasAnswered = true,
            selectedAnswerIndex = selectedIndex,
            mistakes = if (isCorrect) currentState.mistakes else currentState.mistakes + 1
        )
    }

    fun nextQuestion() {
        val currentState = _uiState.value
        val nextIndex = currentState.currentQuestionIndex + 1

        if (nextIndex < currentState.questions.size) {
            _uiState.value = currentState.copy(
                currentQuestionIndex = nextIndex,
                answeredCount = currentState.answeredCount + 1,
                hasAnswered = false,
                selectedAnswerIndex = null
            )
        } else {
            _uiState.value = currentState.copy(
                answeredCount = currentState.answeredCount + 1,
                hasAnswered = false,
                selectedAnswerIndex = null
            )
        }
    }

    fun resetGame() {
        _uiState.value = GameUiState()
    }
}