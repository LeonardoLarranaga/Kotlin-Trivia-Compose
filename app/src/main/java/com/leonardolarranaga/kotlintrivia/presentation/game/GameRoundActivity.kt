package com.leonardolarranaga.kotlintrivia.presentation.game

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.leonardolarranaga.kotlintrivia.data.QuestionDatabase
import com.leonardolarranaga.kotlintrivia.data.RoundScoreDatabase
import com.leonardolarranaga.kotlintrivia.ui.theme.KotlinTriviaTheme

class GameRoundActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val roundScoreDatabase = RoundScoreDatabase(this)
        val questionDatabase = QuestionDatabase(this)
        val questionAmount = 6

        enableEdgeToEdge()
        setContent {
            KotlinTriviaTheme {
                GameRoundScreen(
                    finish = ::finish,
                    questionDatabase = questionDatabase,
                    questionAmount = questionAmount,
                    roundScoreDatabase = roundScoreDatabase
                )
            }
        }
    }
}