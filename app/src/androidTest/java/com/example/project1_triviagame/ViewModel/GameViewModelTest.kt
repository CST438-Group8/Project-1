package com.example.project1_triviagame.ViewModel

import com.example.project1_triviagame.network.TriviaQuestion
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GameViewModelTest {

    private lateinit var viewModel: GameViewModel

    @Before
    fun setup() {
        viewModel = GameViewModel()
    }

    private fun fakeQuestions(): List<TriviaQuestion> {
        return listOf(
            TriviaQuestion(
                question = "Test Question",
                correctAnswer = "Correct",
                answers = listOf("Correct", "Wrong1", "Wrong2", "Wrong3"),
                category = "General",
                difficulty = "easy"
            )
        )
    }

    @Test
    fun answerQuestion_correctAnswer_doesNotIncreaseMistakes() {
        // Inject fake state
        viewModel.resetGame()
        viewModel.loadQuestionsForTest(fakeQuestions())

        viewModel.answerQuestion(0) // index 0 = "Correct"

        val state = viewModel.uiState.value

        assertEquals(0, state.mistakes)
        assertTrue(state.hasAnswered)
    }

    @Test
    fun answerQuestion_wrongAnswer_increasesMistakes() {
        viewModel.resetGame()
        viewModel.loadQuestionsForTest(fakeQuestions())

        viewModel.answerQuestion(1) // index 1 = "Wrong1"

        val state = viewModel.uiState.value

        assertEquals(1, state.mistakes)
        assertTrue(state.hasAnswered)
    }

    @Test
    fun nextQuestion_incrementsAnsweredCount() {
        viewModel.resetGame()
        viewModel.loadQuestionsForTest(fakeQuestions())

        viewModel.answerQuestion(0)
        viewModel.nextQuestion()

        val state = viewModel.uiState.value

        assertEquals(1, state.answeredCount)
    }

    @Test
    fun gameOver_whenMistakesReachSix() {
        viewModel.resetGame()
        viewModel.loadQuestionsForTest(fakeQuestions())

        repeat(6) {
            viewModel.answerQuestion(1)
            viewModel.nextQuestion()
        }

        val state = viewModel.uiState.value

        assertTrue(state.gameOver)
        assertTrue(state.isLoss)
    }

    @Test
    fun isWin_whenAnsweredTenAndLessThanSixMistakes() {
        viewModel.resetGame()
        viewModel.loadQuestionsForTest(fakeQuestions())

        repeat(10) {
            viewModel.answerQuestion(0)
            viewModel.nextQuestion()
        }

        val state = viewModel.uiState.value

        assertTrue(state.gameOver)
        assertTrue(state.isWin)
        assertFalse(state.isLoss)
    }
}
