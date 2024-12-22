package com.example.scratchcardcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.scratchcardcompose.ui.ScreenExampleOfScratchCard
import com.example.scratchcardcompose.ui.theme.ScratchCardComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScratchCardComposeTheme {
               ScreenExampleOfScratchCard()
            }
        }
    }
}