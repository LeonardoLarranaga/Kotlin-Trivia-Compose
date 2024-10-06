package com.leonardolarranaga.kotlintrivia.presentation.game

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.leonardolarranaga.kotlintrivia.R
import com.leonardolarranaga.kotlintrivia.data.Question


@Composable
fun GameQuestion(
    modifier: Modifier,
    question: Question,
    onAnswerSelected: (String) -> Unit,
    showCorrectAnswer: Boolean,
    selectedAnswer: String
) {
    Column(modifier = modifier) {
        Text(
            question.text,
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier
                .padding(top = 12.dp)
                .padding(bottom = 20.dp)
                .padding(horizontal = 20.dp)
                .background(
                    color = colorResource(id = R.color.kotlin_purple_blue),
                    shape = RoundedCornerShape(20),
                )
                .padding(vertical = 12.dp)
                .height(128.dp)
                .wrapContentHeight(Alignment.CenterVertically)
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            val shuffledOptions = remember(question) { question.options.toList().shuffled() }
            for (answer in shuffledOptions) {
                GameAnswerButton(
                    answer = answer,
                    onAnswerSelected = onAnswerSelected,
                    showCorrectAnswer = showCorrectAnswer,
                    correctAnswer = question.options[question.correctAnswerIndex],
                    selectedAnswer = selectedAnswer
                )
            }
        }
    }
}