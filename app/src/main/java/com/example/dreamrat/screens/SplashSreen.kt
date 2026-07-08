package com.example.dreamrat.screens

import androidx.compose.ui.tooling.preview.Preview
import com.example.dreamrat.ui.theme.DreamRATTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dreamrat.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onSplashFinished: () -> Unit
) {

    var progress by remember {
        mutableFloatStateOf(0f)
    }

    var percentage by remember {
        mutableIntStateOf(0)
    }

    LaunchedEffect(Unit) {

        while (percentage < 100) {
            delay(20)
            percentage++
            progress = percentage / 100f
        }
        delay(300)
        onSplashFinished()
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Image(
            painter = painterResource(id = R.drawable.splash_image),
            contentDescription = "Splash Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(horizontal = 24.dp, vertical = 32.dp),

            horizontalAlignment = Alignment.CenterHorizontally

        ) {

            Text(
                text = "INITIALIZING SECURE CONNECTION...",
                color = Color(0xFFFF2D2D),
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            LinearProgressIndicator(
                progress = { progress },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .clip(RoundedCornerShape(100.dp)),
                color = Color.Red,
                trackColor = Color(0xFF2A0000)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "$percentage%",
                color = Color.Red,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
