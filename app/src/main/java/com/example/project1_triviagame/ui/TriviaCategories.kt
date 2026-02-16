package com.example.project1_triviagame.ui

import androidx.annotation.DrawableRes
import com.example.project1_triviagame.R

data class TriviaCategory(
    val name: String,
    val id: Int,
    @DrawableRes val imageRes: Int
)

val TRIVIA_CATEGORIES = listOf(
    TriviaCategory("Video Games", 15, R.drawable.videogames_fig),
    TriviaCategory("Tech", 18, R.drawable.tech_fig),
    TriviaCategory("Sports", 21, R.drawable.sports_fig),
    TriviaCategory("Geography", 22, R.drawable.geography_fig),
    TriviaCategory("Film", 11, R.drawable.film_fig),
    TriviaCategory("Music", 12, R.drawable.music_fig)
)