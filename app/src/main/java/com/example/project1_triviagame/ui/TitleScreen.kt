package com.example.project1_triviagame.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project1_triviagame.ui.theme.Project1_TriviaGameTheme
import com.example.project1_triviagame.ui.theme.background
import androidx.compose.foundation.background
import kotlinx.coroutines.delay

@Composable
fun TitleScreen(onTap: () -> Unit = {}) {
    var showTapText by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(1000)
        showTapText = true
    }

    val tapAlpha by animateFloatAsState(
        targetValue = if (showTapText) 1f else 0f,
        animationSpec = tween(durationMillis = 500),
        label = "tapTextAlpha"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(background)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) { onTap() },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Hangman Trivia",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Tap anywhere to start",
            fontSize = 14.sp,
            color = Color(0xFFAAAAAA),
            modifier = Modifier.alpha(tapAlpha)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TitleScreenPreview() {
    Project1_TriviaGameTheme {
        TitleScreen()
    }
}