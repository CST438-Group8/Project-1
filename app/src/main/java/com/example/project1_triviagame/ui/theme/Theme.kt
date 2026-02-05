package com.example.project1_triviagame.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val AppDarkColorScheme = darkColorScheme(
    primary = Color(0xFF8C817A),
    onPrimary = Color.White,
    background = Color(0xFF0B2A4A),
    surface = Color(0xFF0B2A4A),
    onBackground = Color.White,
    onSurface = Color.White
)

@Composable
fun Project1_TriviaGameTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = AppDarkColorScheme,
        content = content
    )
}