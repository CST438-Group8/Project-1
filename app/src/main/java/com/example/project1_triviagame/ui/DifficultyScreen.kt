package com.example.project1_triviagame.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project1_triviagame.ui.theme.AmaticSC
import com.example.project1_triviagame.ui.theme.Project1_TriviaGameTheme
import com.example.project1_triviagame.ui.theme.background
import com.example.project1_triviagame.ui.theme.buttoncolor

@Composable
fun DifficultyScreen(
    onDifficultySelected: (String) -> Unit = {},
    onBack: () -> Unit = {}
) {
    val interaction = remember { MutableInteractionSource() }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(background),
        contentAlignment = Alignment.Center
    ) {
        // Back button (top-left)
        Text(
            text = "◀ Themes",
            color = Color.White,
            fontSize = 28.sp,
            fontFamily = AmaticSC,
            fontWeight = FontWeight.SemiBold,
            style = TextStyle(
                shadow = Shadow(
                    color = Color.White,
                    blurRadius = 18f
                )
            ),
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 20.dp, top = 24.dp)
                // ✅ FIX: disable ripple/indication to avoid PlatformRipple crash
                .clickable(
                    interactionSource = interaction,
                    indication = null
                ) { onBack() }
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(90.dp)
        ) {
            Text(
                text = "Choose Difficulty",
                color = Color.White,
                fontSize = 50.sp,
                fontFamily = AmaticSC,
                fontWeight = FontWeight.SemiBold
            )
            Easybutton(onClick = { onDifficultySelected("easy") })
            Mediumbutton(onClick = { onDifficultySelected("medium") })
            Hardbutton(onClick = { onDifficultySelected("hard") })
        }
    }
}

@Composable
fun Easybutton(onClick: () -> Unit = {}) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = buttoncolor
        ),
        modifier = Modifier
            .width(300.dp)
            .height(70.dp)
    ) {
        Text(
            "Easy",
            fontSize = 40.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = AmaticSC
        )
    }
}

@Composable
fun Mediumbutton(onClick: () -> Unit = {}) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = buttoncolor
        ),
        modifier = Modifier
            .width(300.dp)
            .height(70.dp)
    ) {
        Text(
            "Medium",
            fontSize = 40.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = AmaticSC,
        )
    }
}

@Composable
fun Hardbutton(onClick: () -> Unit = {}) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = buttoncolor
        ),
        modifier = Modifier
            .width(300.dp)
            .height(70.dp)
    ) {
        Text(
            "Hard",
            fontSize = 40.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = AmaticSC,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DifficultyScreenPreview() {
    Project1_TriviaGameTheme {
        DifficultyScreen()
    }
}