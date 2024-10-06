package com.leonardolarranaga.kotlintrivia.data

data class Question(
    val text: String,
    val options: Array<String>,
    val correctAnswerIndex: Int
)