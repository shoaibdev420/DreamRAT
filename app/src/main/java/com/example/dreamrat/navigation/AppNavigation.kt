package com.example.dreamrat.navigation

import androidx.compose.runtime.*
import com.example.dreamrat.screens.HomeScreen
import com.example.dreamrat.screens.SplashScreen
import com.example.dreamrat.features.home.quick_action.location.LocationScreen

enum class Screen {
    Splash,
    Home,
    Location
}

@Composable
fun AppNavigation() {
    var currentScreen by remember { mutableStateOf(Screen.Splash) }

    when (currentScreen) {
        Screen.Splash -> {
            SplashScreen(
                onSplashFinished = {
                    currentScreen = Screen.Home
                }
            )
        }
        Screen.Home -> {
            HomeScreen(
                onNavigateToLocation = {
                    currentScreen = Screen.Location
                }
            )
        }
        Screen.Location -> {
            LocationScreen(
                onBackClick = {
                    currentScreen = Screen.Home
                }
            )
        }
    }
}