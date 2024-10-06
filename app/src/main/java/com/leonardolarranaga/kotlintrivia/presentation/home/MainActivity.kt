package com.leonardolarranaga.kotlintrivia.presentation.home

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.leonardolarranaga.kotlintrivia.presentation.game.GameRoundActivity
import com.leonardolarranaga.kotlintrivia.presentation.home.roundscores.RoundScoresActivity
import com.leonardolarranaga.kotlintrivia.ui.theme.KotlinTriviaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KotlinTriviaTheme {
                Scaffold { innerPadding ->
                    Home(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        startRound = ::startRound,
                        showScores = ::showScores
                    )
                }
            }
        }
    }

    fun startRound() {
        val intent = Intent(this, GameRoundActivity::class.java)
        startActivity(intent)
    }

    fun showScores() {
        val intent = Intent(this, RoundScoresActivity::class.java)
        startActivity(intent)
    }
}