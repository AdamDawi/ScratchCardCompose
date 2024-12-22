package com.example.scratchcardcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.scratchcardcompose.ui.HomeScreen
import com.example.scratchcardcompose.ui.screen_with_example.ScratchCardScreenExampleLidl
import com.example.scratchcardcompose.ui.screen_with_example.ScratchCardScreenExampleMovie
import com.example.scratchcardcompose.ui.theme.ScratchCardComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScratchCardComposeTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "home"){
                    composable("home"){
                        HomeScreen(
                            onNavigateToLidlScreen = {
                                navController.navigate("lidl")
                            },
                            onNavigateToMovieScreen = {
                                navController.navigate("movie")
                            }
                        )
                    }
                    composable("lidl"){
                        ScratchCardScreenExampleLidl()
                    }
                    composable("movie"){
                        ScratchCardScreenExampleMovie()
                    }
                }
            }
        }
    }
}