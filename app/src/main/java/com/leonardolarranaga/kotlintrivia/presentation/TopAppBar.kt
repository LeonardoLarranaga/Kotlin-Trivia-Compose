package com.leonardolarranaga.kotlintrivia.presentation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    backButtonAction: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            KotlinTriviaLogo(
                modifier = Modifier.fillMaxWidth(0.3f),
                textFontSize = MaterialTheme.typography.headlineSmall.fontSize
            )
        },
        navigationIcon = {
            IconButton(onClick = backButtonAction) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                    contentDescription = "Back"
                )
            }
        }
    )
}