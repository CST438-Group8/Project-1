package com.example.project1_triviagame.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project1_triviagame.ui.theme.AmaticSC
import com.example.project1_triviagame.ui.theme.Project1_TriviaGameTheme
import com.example.project1_triviagame.ui.theme.background
import com.example.project1_triviagame.ui.theme.buttoncolor

@Composable
fun DifficultyScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(background), //background color from theme
        contentAlignment = Alignment.Center
    ) {
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
            Easybutton()
            Mediumbutton()
            Hardbutton()
        }
    }
}

@Composable
fun Easybutton() {
    Button(
        onClick = { println("Easy button clicked!") },
        colors = ButtonDefaults.buttonColors(
            containerColor = buttoncolor  // Using buttoncolor from theme
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
fun Mediumbutton() {
    Button(
        onClick = { println("Medium button clicked!") },
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
fun Hardbutton() {
    Button(
        onClick = { println("Hard button clicked!") },
        colors = ButtonDefaults.buttonColors(
            containerColor = buttoncolor  // Using buttoncolor from theme
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
fun HomeScreenPreview() {
    Project1_TriviaGameTheme {
        DifficultyScreen()
    }
}