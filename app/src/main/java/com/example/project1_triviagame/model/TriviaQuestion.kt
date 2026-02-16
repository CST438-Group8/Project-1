
package com.example.project1_triviagame.model

data class TriviaQuestion(
    val category: String,
    val difficulty: String,
    val question: String,
    val correctAnswer: String,
    val incorrectAnswers: List<String>
)
