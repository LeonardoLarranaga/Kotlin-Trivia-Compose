package com.leonardolarranaga.kotlintrivia.presentation.home.roundscores

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.leonardolarranaga.kotlintrivia.presentation.TopAppBar
import com.leonardolarranaga.kotlintrivia.data.RoundScoreDatabase
import com.leonardolarranaga.kotlintrivia.ui.theme.KotlinTriviaTheme

class RoundScoresActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val roundScoreDatabase = RoundScoreDatabase(this)
        enableEdgeToEdge()
        setContent {
            KotlinTriviaTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            backButtonAction = ::finish
                        )
                    }
                ) { innerPadding ->
                    RoundScoresList(
                        modifier = Modifier.padding(innerPadding),
                        roundScores = roundScoreDatabase.roundScores
                    )
                }
            }
        }
    }
}