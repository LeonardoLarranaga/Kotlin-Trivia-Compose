package com.leonardolarranaga.kotlintrivia.presentation.game

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.leonardolarranaga.kotlintrivia.R
import com.leonardolarranaga.kotlintrivia.data.Question
import com.leonardolarranaga.kotlintrivia.data.RoundScore
import com.leonardolarranaga.kotlintrivia.data.RoundScoreDatabase
import com.leonardolarranaga.kotlintrivia.data.RoundScoreQuestion

@Composable
fun GameRound(
    modifier: Modifier,
    roundScoreDatabase: RoundScoreDatabase,
    roundScore: MutableState<RoundScore>,
    questions: List<Question>,
    currentQuestionNumber: MutableState<Int>,
    selectedAnswer: MutableState<String>,
    roundNumber: Int,
    finish: () -> Unit,
    startNewRound: () -> Unit
) {
    var currentQuestion by remember(currentQuestionNumber.value) { mutableStateOf(questions[currentQuestionNumber.value]) }
    var showFloatingActionButton by remember { mutableStateOf(false) }
    var finishedGame by remember { mutableStateOf(false) }

    fun onAnswerSelected(answer: String) {
        selectedAnswer.value = answer
        val selectedAnswerIndex = currentQuestion.options.indexOf(answer)

        roundScore.value.questions.add(
            RoundScoreQuestion(
                question = currentQuestion.text,
                correctAnswer = currentQuestion.options[currentQuestion.correctAnswerIndex],
                selectedAnswer = answer
            )
        )

        if (selectedAnswerIndex == currentQuestion.correctAnswerIndex) {
            roundScore.value.score += 1
        } else {
            roundScore.value.mistakes += 1
        }

        if (currentQuestionNumber.value == questions.size - 1) {
            roundScoreDatabase.saveRoundScore(roundScore.value)
            finishedGame = true
        }

        showFloatingActionButton = true
    }

    Column(
        modifier = modifier
    ) {
        Text(
            text = "Ronda $roundNumber",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp)
        )

        Text(
            text = "Pregunta ${currentQuestionNumber.value + 1}",
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp)
        )

        GameQuestion(
            modifier = Modifier
                .fillMaxHeight(1f),
            question = currentQuestion,
            onAnswerSelected = { answer -> onAnswerSelected(answer) },
            showCorrectAnswer = showFloatingActionButton,
            selectedAnswer = selectedAnswer.value
        )
    }

    if (showFloatingActionButton) {
        FloatingActionButton(
            onClick = {
                if (currentQuestionNumber.value < questions.size - 1) {
                    currentQuestionNumber.value += 1
                    currentQuestion = questions[currentQuestionNumber.value]
                    selectedAnswer.value = ""
                    showFloatingActionButton = false
                }
            },
            shape = CircleShape,
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.BottomEnd)
                .padding(end = 25.dp, bottom = 50.dp)
                .size(55.dp),
            containerColor = colorResource(id = R.color.kotlin_purple),
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = "Siguiente pregunta",
                modifier = Modifier.size(30.dp),
                tint = Color.White
            )
        }
    }

    if (finishedGame) {
        AlertDialog(
            onDismissRequest = {},
            dismissButton = {
                Button(onClick = finish) {
                    Text("Ir a Home")
                }
            },
            confirmButton = {
                Button(onClick = {
                    startNewRound()
                    finishedGame = false
                    showFloatingActionButton = false
                    currentQuestion = questions[0]
                }) {
                    Text("Nueva Ronda")
                }
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Create,
                    contentDescription = "Lápiz",
                    modifier = Modifier.size(35.dp)
                )
            },
            title = {
                Text(
                    text = "¡Ronda terminada!",
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Text(
                    text = "Has terminado la ronda con ${roundScore.value.score} aciertos y ${roundScore.value.mistakes} errores.",
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
        )
    }
}