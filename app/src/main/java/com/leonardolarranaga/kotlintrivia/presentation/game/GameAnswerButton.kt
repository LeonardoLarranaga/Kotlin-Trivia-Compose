package com.leonardolarranaga.kotlintrivia.presentation.game

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.leonardolarranaga.kotlintrivia.presentation.kotlinBrush

@Composable
fun GameAnswerButton(
    answer: String,
    onAnswerSelected: (String) -> Unit,
    showCorrectAnswer: Boolean,
    correctAnswer: String,
    selectedAnswer: String
) {
    Button(
        onClick = { onAnswerSelected(answer) },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent
        ),
        enabled = !showCorrectAnswer
    ) {
        Text(
            text = answer,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            fontFamily = FontFamily.Monospace,
            color = Color.Black,
            modifier = Modifier
                .fillMaxWidth(1f)
                .height(85.dp)
                .border(
                    width = 3.dp,
                    brush = kotlinBrush(),
                    shape = RoundedCornerShape(20)
                )
                .background(
                    color = when {
                        showCorrectAnswer && answer == correctAnswer -> Color.Green
                        showCorrectAnswer && answer == selectedAnswer -> Color.Red
                        else -> Color.Transparent
                    },
                    shape = RoundedCornerShape(20),
                )
                .padding(vertical = 12.dp)
                .wrapContentHeight(Alignment.CenterVertically)
        )
    }
}