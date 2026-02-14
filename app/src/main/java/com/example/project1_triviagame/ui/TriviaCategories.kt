package com.example.project1_triviagame.ui

// ui for categories
data class TriviaCategory(val name: String, val id: Int)

// all 6 categories
val TRIVIA_CATEGORIES = listOf(
    TriviaCategory("Video Games", 15),
    TriviaCategory("Computers", 18),
    TriviaCategory("Sports", 21),
    TriviaCategory("Geography", 22),
    TriviaCategory("Film", 11),
    TriviaCategory("Music", 12)
)
