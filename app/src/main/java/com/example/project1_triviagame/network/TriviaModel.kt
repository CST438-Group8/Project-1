package com.example.project1_triviagame.network

data class TriviaQuestion(
    val question: String,
    val correctAnswer: String,
    val answers: List<String>, // includes correct + incorrect
    val category: String,
    val difficulty: String
)
