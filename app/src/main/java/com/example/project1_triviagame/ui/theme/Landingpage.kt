package com.example.project1_triviagame.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.project1_triviagame.ui.theme.Project1_TriviaGameTheme

@Composable
fun HomeScreen() {
    val backgroundColor = Color(0xFF0B2A4A)
    val accentColor = Color(0xFF8C817A)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(24.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Hangman Trivia",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(24.dp))
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = backgroundColor.copy(alpha = 0.4f)
                    )
                ) {
                    Canvas(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(24.dp)
                    ) {
                        val lineColor = Color.White
                        val stroke = Stroke(width = 10f, cap = StrokeCap.Round)

                        val w = size.width
                        val h = size.height

                        drawLine(
                            color = lineColor,
                            start = androidx.compose.ui.geometry.Offset(w * 0.2f, h * 0.9f),
                            end = androidx.compose.ui.geometry.Offset(w * 0.8f, h * 0.9f),
                            strokeWidth = stroke.width,
                            cap = stroke.cap
                        )
                        drawLine(
                            color = lineColor,
                            start = androidx.compose.ui.geometry.Offset(w * 0.3f, h * 0.9f),
                            end = androidx.compose.ui.geometry.Offset(w * 0.3f, h * 0.2f),
                            strokeWidth = stroke.width,
                            cap = stroke.cap
                        )
                        drawLine(
                            color = lineColor,
                            start = androidx.compose.ui.geometry.Offset(w * 0.3f, h * 0.2f),
                            end = androidx.compose.ui.geometry.Offset(w * 0.65f, h * 0.2f),
                            strokeWidth = stroke.width,
                            cap = stroke.cap
                        )
                        drawLine(
                            color = lineColor,
                            start = androidx.compose.ui.geometry.Offset(w * 0.65f, h * 0.2f),
                            end = androidx.compose.ui.geometry.Offset(w * 0.65f, h * 0.3f),
                            strokeWidth = stroke.width,
                            cap = stroke.cap
                        )
                        drawCircle(
                            color = lineColor,
                            radius = 28f,
                            center = androidx.compose.ui.geometry.Offset(w * 0.65f, h * 0.38f),
                            style = Stroke(width = 8f)
                        )
                        drawLine(
                            color = lineColor,
                            start = androidx.compose.ui.geometry.Offset(w * 0.65f, h * 0.42f),
                            end = androidx.compose.ui.geometry.Offset(w * 0.65f, h * 0.62f),
                            strokeWidth = stroke.width,
                            cap = stroke.cap
                        )
                        drawLine(
                            color = lineColor,
                            start = androidx.compose.ui.geometry.Offset(w * 0.65f, h * 0.48f),
                            end = androidx.compose.ui.geometry.Offset(w * 0.58f, h * 0.58f),
                            strokeWidth = stroke.width,
                            cap = stroke.cap
                        )
                        drawLine(
                            color = lineColor,
                            start = androidx.compose.ui.geometry.Offset(w * 0.65f, h * 0.48f),
                            end = androidx.compose.ui.geometry.Offset(w * 0.72f, h * 0.58f),
                            strokeWidth = stroke.width,
                            cap = stroke.cap
                        )
                        drawLine(
                            color = lineColor,
                            start = androidx.compose.ui.geometry.Offset(w * 0.65f, h * 0.62f),
                            end = androidx.compose.ui.geometry.Offset(w * 0.58f, h * 0.78f),
                            strokeWidth = stroke.width,
                            cap = stroke.cap
                        )
                        drawLine(
                            color = lineColor,
                            start = androidx.compose.ui.geometry.Offset(w * 0.65f, h * 0.62f),
                            end = androidx.compose.ui.geometry.Offset(w * 0.72f, h * 0.78f),
                            strokeWidth = stroke.width,
                            cap = stroke.cap
                        )
                    }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = accentColor,
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "Play")
                }

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedButton(
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "Unlocks & Levels")
                }

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Wins: 0    Losses: 0",
                    color = Color.White,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    Project1_TriviaGameTheme {
        HomeScreen()
    }
}
