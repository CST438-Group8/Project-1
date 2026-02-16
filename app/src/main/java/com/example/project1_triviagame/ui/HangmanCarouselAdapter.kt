package com.example.project1_triviagame.ui

import androidx.annotation.DrawableRes

// Compose-only model for one carousel option (no RecyclerView, no XML)
data class HangmanThemeOption(
    val id: Int,
    val label: String,
    val textColorHex: String,
    @DrawableRes val imageRes: Int
)