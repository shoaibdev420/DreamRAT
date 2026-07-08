package com.example.dreamrat.navigation

import androidx.compose.runtime.*
import com.example.dreamrat.screens.HomeScreen
import com.example.dreamrat.screens.SplashScreen

@Composable
fun AppNavigation() {

    var showSplash by remember {
        mutableStateOf(true)
    }

    if (showSplash) {

        SplashScreen(
            onSplashFinished = {
                showSplash = false
            }
        )

    } else {

        HomeScreen()

    }
}