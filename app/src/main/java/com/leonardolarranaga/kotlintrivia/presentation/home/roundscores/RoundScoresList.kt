package com.leonardolarranaga.kotlintrivia.presentation.home.roundscores

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.leonardolarranaga.kotlintrivia.data.RoundScore

@Composable
fun RoundScoresList(
    modifier: Modifier,
    roundScores: List<RoundScore>,
) {
    Column(
        modifier = modifier.padding(horizontal = 12.dp)
    ) {
        Text(
            text = "Scores",
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 3.dp)
        )
        
        if (roundScores.isEmpty()) {
            Spacer(modifier = Modifier.fillMaxHeight(0.5f))
            Text(
                text = "No hay ninguna ronda registrada.",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.fillMaxWidth(),
            )
        } else {
            LazyColumn {
                itemsIndexed(roundScores) { index, roundScore ->
                    RoundScoreCard(
                        modifier = Modifier.padding(vertical = 12.dp),
                        roundScore = roundScore,
                        index = index
                    )
                }
            }
        }
    }
}