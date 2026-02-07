package com.example.project1_triviagame.ui

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
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project1_triviagame.ui.theme.Project1_TriviaGameTheme
import com.example.project1_triviagame.ui.theme.background
import com.example.project1_triviagame.ui.theme.buttoncolor

@Composable
fun GameScreen() {
    var answeredCount by remember { mutableIntStateOf(0) }
    var mistakes by remember { mutableIntStateOf(0) }

    val gameOver = answeredCount >= 10 || mistakes >= 6

    val questionText = when {
        mistakes >= 6 -> "Game Over (placeholder)"
        answeredCount >= 10 -> "Finished (placeholder)"
        else -> "Question will load from API..."
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(background)
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Question progress bar
        LinearProgressIndicator(
            progress = { answeredCount / 10f },
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp),
            color = buttoncolor,
            trackColor = Color.White.copy(alpha = 0.3f)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Progress: $answeredCount/10",
            color = Color.White,
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Question text
        Text(
            text = questionText,
            color = Color.White,
            fontSize = 18.sp,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Hangman placeholder icon
        Icon(
            painter = painterResource(id = android.R.drawable.ic_menu_help),
            contentDescription = "Hangman placeholder",
            tint = Color.White,
            modifier = Modifier.size(200.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Hangman mistake progress bar
        LinearProgressIndicator(
            progress = { mistakes / 6f },
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp),
            color = Color.Red,
            trackColor = Color.White.copy(alpha = 0.3f)
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "Mistakes: $mistakes/6",
            color = Color.White,
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Answer buttons
        val answerLabels = listOf("A", "B", "C", "D")
        answerLabels.forEach { label ->
            Button(
                onClick = {
                    if (!gameOver) {
                        answeredCount = (answeredCount + 1).coerceAtMost(10)
                        if (answeredCount % 2 == 0) {
                            mistakes = (mistakes + 1).coerceAtMost(6)
                        }
                    }
                },
                enabled = !gameOver,
                colors = ButtonDefaults.buttonColors(
                    containerColor = buttoncolor,
                    contentColor = Color.White,
                    disabledContainerColor = buttoncolor.copy(alpha = 0.4f),
                    disabledContentColor = Color.White.copy(alpha = 0.5f)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp)
            ) {
                Text(text = "Answer $label", fontSize = 16.sp)
            }
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
