package com.leonardolarranaga.kotlintrivia.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.leonardolarranaga.kotlintrivia.R
import com.leonardolarranaga.kotlintrivia.presentation.KotlinTriviaLogo

@Composable
fun Home(
    modifier: Modifier,
    startRound: () -> Unit,
    showScores: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.weight(1f))

        KotlinTriviaLogo()

        Button(
            onClick = startRound,
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.kotlin_purple),
            ),
            modifier = Modifier
                .padding(top = 25.dp)
                .fillMaxWidth(0.75f)
        ) {
            Text(
                text = "Nueva Ronda",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = showScores,
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.kotlin_rose),
            ),
            modifier = Modifier.padding(bottom = 32.dp)
        ) {
            Text(
                text = "Ver Scores",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}
