package com.leonardolarranaga.kotlintrivia.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import com.leonardolarranaga.kotlintrivia.R

@Composable
fun KotlinTriviaLogo(
    modifier: Modifier = Modifier.fillMaxWidth(0.75f),
    textFontSize: TextUnit = MaterialTheme.typography.displayLarge.fontSize,
    textFontWeight: FontWeight = FontWeight.ExtraBold
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.kotlin_logo),
            contentDescription = "Kotlin Logo",
            modifier = modifier
        )

        Text(
            text = "Trivia",
            style = TextStyle(
                brush = kotlinBrush(),
                fontSize = textFontSize,
                fontWeight = textFontWeight
            )
        )
    }
}

@Composable
fun kotlinBrush(): Brush {
    return Brush.horizontalGradient(
        listOf(
            colorResource(id = R.color.kotlin_purple_blue),
            colorResource(id = R.color.kotlin_purple),
            colorResource(id = R.color.kotlin_rose)
        )
    )
}