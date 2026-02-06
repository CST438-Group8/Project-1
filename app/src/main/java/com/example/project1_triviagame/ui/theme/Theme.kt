package com.example.project1_triviagame.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.project1_triviagame.R

@Composable
fun Project1_TriviaGameTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme {
        content()
    }
}

val background = Color(0xFF0B2A4A)
val buttoncolor = Color(0xFF8C817A)

val AmaticSC = FontFamily(
    Font(R.font.amaticsc_regular, FontWeight.Normal),
    Font(R.font.amaticsc_bold, FontWeight.Bold)
)


val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = AmaticSC,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    titleLarge = TextStyle(
        fontFamily = AmaticSC,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp
    ))
