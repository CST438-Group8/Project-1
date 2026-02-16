package com.example.project1_triviagame.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.project1_triviagame.ui.theme.Project1_TriviaGameTheme
import com.example.project1_triviagame.ui.theme.background
import com.example.project1_triviagame.ui.theme.buttoncolor
import com.example.project1_triviagame.ViewModel.GameViewModel

@Composable
fun GameScreen(
    difficulty: String = "easy",
    categoryId: Int = 18,
    onGameFinished: (Boolean) -> Unit = {},
    viewModel: GameViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    // Load questions when screen is first displayed
    LaunchedEffect(difficulty, categoryId) {
        viewModel.loadQuestions(difficulty, categoryId)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(background)
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when {
            uiState.isLoading -> {
                // Loading state
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator(color = buttoncolor)
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Loading questions...",
                        color = Color.White,
                        fontSize = 18.sp
                    )
                }
            }

            uiState.error != null -> {
                // Error state
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = uiState.error ?: "Unknown error",
                        color = Color.Red,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = { viewModel.loadQuestions(difficulty, categoryId) },
                        colors = ButtonDefaults.buttonColors(containerColor = buttoncolor)
                    ) {
                        Text("Retry")
                    }
                }
            }

            else -> {
                // Game content
                GameContent(
                    uiState = uiState,
                    viewModel = viewModel,
                    onGameFinished = onGameFinished
                )
            }
        }
    }
}

@Composable
private fun GameContent(
    uiState: com.example.project1_triviagame.ViewModel.GameUiState,
    viewModel: GameViewModel,
    onGameFinished: (Boolean) -> Unit
) {
    // Question progress bar
    LinearProgressIndicator(
        progress = { uiState.answeredCount / 10f },
        modifier = Modifier
            .fillMaxWidth()
            .height(8.dp),
        color = buttoncolor,
        trackColor = Color.White.copy(alpha = 0.3f)
    )

    Spacer(modifier = Modifier.height(8.dp))

    Text(
        text = "Progress: ${uiState.answeredCount}/10",
        color = Color.White,
        fontSize = 16.sp
    )

    Spacer(modifier = Modifier.height(16.dp))

    // Question text or game over message
    if (uiState.gameOver) {
        Text(
            text = if (uiState.isWin) "🎉 You Win!" else "💀 Game Over!",
            color = if (uiState.isWin) Color.Green else Color.Red,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Score: ${uiState.answeredCount - uiState.mistakes}/10",
            color = Color.White,
            fontSize = 24.sp,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { onGameFinished(uiState.isWin) },
            colors = ButtonDefaults.buttonColors(containerColor = buttoncolor),
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
        ) {
            Text("Return to Menu", fontSize = 18.sp)
        }
    } else {
        val currentQuestion = uiState.currentQuestion
        if (currentQuestion != null) {
            Text(
                text = currentQuestion.question,
                color = Color.White,
                fontSize = 18.sp,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
    }

    Spacer(modifier = Modifier.height(16.dp))

    // Hangman drawing - progressively drawn based on mistakes
    HangmanDrawing(
        mistakes = uiState.mistakes,
        modifier = Modifier.size(200.dp)
    )

    Spacer(modifier = Modifier.height(8.dp))

    // Hangman mistake progress bar
    LinearProgressIndicator(
        progress = { uiState.mistakes / 6f },
        modifier = Modifier
            .fillMaxWidth()
            .height(8.dp),
        color = Color.Red,
        trackColor = Color.White.copy(alpha = 0.3f)
    )

    Spacer(modifier = Modifier.height(6.dp))

    Text(
        text = "Mistakes: ${uiState.mistakes}/6",
        color = Color.White,
        fontSize = 16.sp
    )

    Spacer(modifier = Modifier.height(16.dp))

    // Answer buttons
    if (!uiState.gameOver && uiState.currentQuestion != null) {
        val currentQuestion = uiState.currentQuestion!!

        currentQuestion.answers.forEachIndexed { index, answer ->
            val isSelected = uiState.selectedAnswerIndex == index
            val isCorrect = answer == currentQuestion.correctAnswer
            val showFeedback = uiState.hasAnswered

            val backgroundColor = when {
                showFeedback && isSelected && isCorrect -> Color.Green
                showFeedback && isSelected && !isCorrect -> Color.Red
                showFeedback && isCorrect -> Color.Green.copy(alpha = 0.6f)
                else -> buttoncolor
            }

            Button(
                onClick = {
                    if (!uiState.hasAnswered) {
                        viewModel.answerQuestion(index)
                    }
                },
                enabled = !uiState.hasAnswered,
                colors = ButtonDefaults.buttonColors(
                    containerColor = backgroundColor,
                    contentColor = Color.White,
                    disabledContainerColor = backgroundColor,
                    disabledContentColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp)
                    .height(60.dp)
            ) {
                Text(
                    text = answer,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center
                )
            }
        }

        // Next button (only show after answering)
        if (uiState.hasAnswered && !uiState.gameOver) {
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { viewModel.nextQuestion() },
                colors = ButtonDefaults.buttonColors(containerColor = buttoncolor),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
            ) {
                Text("Next Question", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun HangmanDrawing(mistakes: Int, modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val lineColor = Color.White
        val bodyColor = Color.White
        val strokeWidth = 6f
        val w = size.width
        val h = size.height

        // Gallows: base
        drawLine(
            color = lineColor,
            start = Offset(w * 0.1f, h * 0.95f),
            end = Offset(w * 0.6f, h * 0.95f),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round
        )
        // Gallows: vertical pole
        drawLine(
            color = lineColor,
            start = Offset(w * 0.25f, h * 0.95f),
            end = Offset(w * 0.25f, h * 0.05f),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round
        )
        // Gallows: top beam
        drawLine(
            color = lineColor,
            start = Offset(w * 0.25f, h * 0.05f),
            end = Offset(w * 0.65f, h * 0.05f),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round
        )
        // Gallows: rope
        drawLine(
            color = lineColor,
            start = Offset(w * 0.65f, h * 0.05f),
            end = Offset(w * 0.65f, h * 0.18f),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round
        )

        // 1) Head
        if (mistakes >= 1) {
            drawCircle(
                color = bodyColor,
                radius = w * 0.08f,
                center = Offset(w * 0.65f, h * 0.26f),
                style = Stroke(width = strokeWidth)
            )
        }

        // 2) Body
        if (mistakes >= 2) {
            drawLine(
                color = bodyColor,
                start = Offset(w * 0.65f, h * 0.34f),
                end = Offset(w * 0.65f, h * 0.58f),
                strokeWidth = strokeWidth,
                cap = StrokeCap.Round
            )
        }

        // 3) Left arm
        if (mistakes >= 3) {
            drawLine(
                color = bodyColor,
                start = Offset(w * 0.65f, h * 0.40f),
                end = Offset(w * 0.50f, h * 0.52f),
                strokeWidth = strokeWidth,
                cap = StrokeCap.Round
            )
        }

        // 4) Right arm
        if (mistakes >= 4) {
            drawLine(
                color = bodyColor,
                start = Offset(w * 0.65f, h * 0.40f),
                end = Offset(w * 0.80f, h * 0.52f),
                strokeWidth = strokeWidth,
                cap = StrokeCap.Round
            )
        }

        // 5) Left leg
        if (mistakes >= 5) {
            drawLine(
                color = bodyColor,
                start = Offset(w * 0.65f, h * 0.58f),
                end = Offset(w * 0.50f, h * 0.75f),
                strokeWidth = strokeWidth,
                cap = StrokeCap.Round
            )
        }

        // 6) Right leg
        if (mistakes >= 6) {
            drawLine(
                color = bodyColor,
                start = Offset(w * 0.65f, h * 0.58f),
                end = Offset(w * 0.80f, h * 0.75f),
                strokeWidth = strokeWidth,
                cap = StrokeCap.Round
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GameScreenPreview() {
    Project1_TriviaGameTheme {
        GameScreen()
    }
}