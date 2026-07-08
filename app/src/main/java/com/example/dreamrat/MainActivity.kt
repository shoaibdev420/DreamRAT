package com.example.dreamrat
import com.example.dreamrat.navigation.AppNavigation
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.dreamrat.ui.theme.DreamRATTheme

private val activity: Any = TODO()

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            DreamRATTheme {
                AppNavigation()
            }
        }
    }
}