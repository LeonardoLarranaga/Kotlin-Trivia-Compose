package com.leonardolarranaga.kotlintrivia.data

import java.util.Date

data class RoundScore(
    val number: Int,
    val date: Date,
    val questions: MutableList<RoundScoreQuestion>,
    var score: Int,
    var mistakes: Int
)

data class RoundScoreQuestion(
    val question: String,
    val correctAnswer: String,
    val selectedAnswer: String
)