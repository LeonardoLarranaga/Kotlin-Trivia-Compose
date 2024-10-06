package com.leonardolarranaga.kotlintrivia.presentation.game

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.leonardolarranaga.kotlintrivia.R
import com.leonardolarranaga.kotlintrivia.data.QuestionDatabase
import com.leonardolarranaga.kotlintrivia.data.RoundScore
import com.leonardolarranaga.kotlintrivia.data.RoundScoreDatabase
import com.leonardolarranaga.kotlintrivia.presentation.TopAppBar
import java.util.Date

@Composable
fun GameRoundScreen(
    finish: () -> Unit,
    questionDatabase: QuestionDatabase,
    questionAmount: Int,
    roundScoreDatabase: RoundScoreDatabase,
) {
    val currentQuestionNumber = remember { mutableIntStateOf(0) }
    val selectedAnswer = remember { mutableStateOf("") }
    var roundNumber = remember { roundScoreDatabase.roundScores.size + 1 }
    val roundScore = remember { mutableStateOf(
        RoundScore(
            number = roundNumber,
            date = Date(),
            questions = mutableListOf(),
            score = 0,
            mistakes = 0
        )
    ) }
    val questions = remember { mutableStateOf(questionDatabase.getRandomQuestions(questionAmount)) }
    var showFinishEarlyAlert by remember { mutableStateOf(false) }

    fun startNewRound() {
        questions.value = questionDatabase.getRandomQuestions(questionAmount)
        roundNumber += 1
        currentQuestionNumber.intValue = 0
        selectedAnswer.value = ""
        roundScore.value = RoundScore(
            number = roundNumber,
            date = Date(),
            questions = mutableListOf(),
            score = 0,
            mistakes = 0
        )
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                backButtonAction = {
                    if (currentQuestionNumber.intValue > 0 || selectedAnswer.value.isNotEmpty()) {
                        showFinishEarlyAlert = true
                    } else {
                        finish()
                    }
                }
            )
        }
    ) { innerPadding ->
        GameRound(
            modifier = Modifier.padding(innerPadding),
            roundScoreDatabase = roundScoreDatabase,
            roundScore = roundScore,
            questions = questions.value,
            currentQuestionNumber = currentQuestionNumber,
            selectedAnswer = selectedAnswer,
            roundNumber = roundNumber,
            finish = finish,
            startNewRound = { startNewRound() }
        )
    }

    if (showFinishEarlyAlert) {
        AlertDialog(
            onDismissRequest = {},
            confirmButton = {
                Row {
                    Button(
                        onClick = finish,
                        border = BorderStroke(1.dp, colorResource(id = R.color.kotlin_purple_blue)),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent,
                            contentColor = colorResource(id = R.color.kotlin_purple_blue)
                        ),
                        modifier = Modifier.padding(horizontal = 12.dp)
                    ) {
                        Text(
                            "Eliminar",
                            maxLines = 1
                        )
                    }

                    Button(
                        onClick = {
                            roundScoreDatabase.saveRoundScore(roundScore.value)
                            finish()
                        },
                        border = BorderStroke(1.dp, colorResource(id = R.color.kotlin_purple_blue)),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent,
                            contentColor = colorResource(id = R.color.kotlin_purple_blue)
                        )
                    ) {
                        Text("Guardar")
                    }
                }
            },
            title = {
                Text(
                    text = "¿Terminar ronda antes?",
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Column {
                    Text(
                        text = "Puedes eliminar o guardar tu score.",
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 12.dp)
                    )
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { showFinishEarlyAlert = false },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(id = R.color.kotlin_purple_blue)
                        )
                    ) {
                        Text("Continuar Ronda")
                    }
                }
            }
        )
    }
}