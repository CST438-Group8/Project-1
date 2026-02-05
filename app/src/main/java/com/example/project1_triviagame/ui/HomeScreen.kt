package com.example.project1_triviagame.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.project1_triviagame.ui.theme.Project1_TriviaGameTheme

@Composable
fun HomeScreen() {
    Text(text = "Hello World")
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    Project1_TriviaGameTheme {
        HomeScreen()
    }
}
