package com.leonardolarranaga.kotlintrivia.presentation.home.roundscores

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.leonardolarranaga.kotlintrivia.R
import com.leonardolarranaga.kotlintrivia.data.RoundScore
import com.leonardolarranaga.kotlintrivia.presentation.kotlinBrush
import java.text.SimpleDateFormat
import java.util.Locale

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RoundScoreCard(
    modifier: Modifier,
    roundScore: RoundScore,
    index: Int
) {
    val pagerState = rememberPagerState(pageCount = { roundScore.questions.size })
    val showQuestions = remember { mutableStateOf(false) }

    OutlinedCard(
        modifier = modifier.fillMaxWidth(),
        border = BorderStroke(
            width = 2.dp,
            brush = kotlinBrush()
        )
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = "Ronda ${index + 1}",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
            )

            Text(
                text = SimpleDateFormat("dd/MMMM/yyyy - hh:mm", Locale.getDefault()).format(roundScore.date),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 5.dp))

            Row(modifier = Modifier.padding(horizontal = 3.dp)) {
                Text(
                    text = "${roundScore.score} Aciertos",
                    style = MaterialTheme.typography.titleMedium,
                )

                Spacer(Modifier.weight(1f))

                Text(
                    text = "${roundScore.mistakes} Errores",
                    style = MaterialTheme.typography.titleMedium,
                )
            }

            Button(
                onClick = { showQuestions.value = !showQuestions.value },
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.CenterEnd)
                    .padding(end = 5.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.kotlin_purple_blue)
                )
            ) {
                Icon(
                    imageVector = if (showQuestions.value) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = "Ocultar/Mostrar preguntas")
            }

            if (showQuestions.value)
                HorizontalPager(state = pagerState) { page ->
                    val question = roundScore.questions[page]

                    Column(
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .padding(horizontal = 2.dp)
                            .background(
                                color = if (question.correctAnswer == question.selectedAnswer)
                                    colorResource(id = R.color.iguana_green)
                                else
                                    colorResource(id = R.color.kotlin_rose).copy(0.9f),
                                shape = RoundedCornerShape(10)
                            )
                            .height(200.dp)
                            .padding(10.dp)
                    ) {
                        Text(
                            text = question.question,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = question.correctAnswer,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Black
                        )

                        if (question.correctAnswer != question.selectedAnswer) {
                            HorizontalDivider(
                                color = Color.DarkGray,
                                modifier = Modifier.padding(vertical = 5.dp)
                            )

                            Text(
                                text = "Tu respuesta: ${question.selectedAnswer}",
                                style = MaterialTheme.typography.titleMedium,
                            )
                        }
                    }
                }
        }
    }
}
