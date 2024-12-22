package com.example.scratchcardcompose.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen(
    onNavigateToLidlScreen: () -> Unit,
    onNavigateToMovieScreen: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 48.dp),
            text = "Choose example screen",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 32.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = onNavigateToLidlScreen
            ) {
                Text(
                    modifier = Modifier.padding(vertical = 6.dp),
                    text = "Lidl",
                    fontSize = 20.sp
                )
            }
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = onNavigateToMovieScreen
            ) {
                Text(
                    modifier = Modifier.padding(vertical = 6.dp),
                    text = "Movie",
                    fontSize = 20.sp
                )
            }
        }
    }
}