package com.example.dreamrat.screens

// ============================================================
// DreamRAT - Parent Control Application
// File        : HomeScreen.kt
// Purpose     : Main Dashboard Screen
//
// This screen is the first screen after Splash Screen.
//
// Current Status:
// - UI Only
// - Dummy Data
// - No Backend
// - No Database
//
// Future:
// - ViewModel
// - API Integration
// - Live Data
// - Navigation
// ============================================================
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dreamrat.R

// ============================================================
// COLORS
//
// NOTE:
// If you want to change the dashboard theme,
// modify these colors only.
// ============================================================

private val BackgroundColor = Color(0xFF0A0A0A)
private val CardColor = Color(0xFF181818)
private val PrimaryRed = Color(0xFFE53935)
private val OnlineGreen = Color(0xFF3DDC84)
private val OfflineRed = Color(0xFFFF5252)
private val TextWhite = Color.White
private val TextGray = Color(0xFFB0B0B0)

// ============================================================
// DUMMY MODELS
//
// Backend will replace these later.
// ============================================================

data class Device(
    val name: String,
    val model: String,
    val status: String,
    val lastSeen: String
)

data class QuickAction(
    val title: String,
    val subtitle: String
)

// ============================================================
// DUMMY DATA
//
// Replace with API response later.
// ============================================================

private val recentDevices = listOf(
    Device(
        name = "Android Device",
        model = "Android 15",
        status = "Online",
        lastSeen = "Now"
    ),
    Device(
        name = "Samsung S24",
        model = "Android 14",
        status = "Online",
        lastSeen = "2 min ago"
    ),
    Device(
        name = "Redmi Note 13",
        model = "Android 13",
        status = "Offline",
        lastSeen = "1 hour ago"
    )
)

private val quickActions = listOf(

    QuickAction("Live Screen", "View Live"),

    QuickAction("Messages", "Read SMS"),

    QuickAction("Camera", "Take Photo"),

    QuickAction("Microphone", "Listen Live"),

    QuickAction("Location", "Track Device"),

    QuickAction("Lock Device", "Lock Now"),

    QuickAction("File Manager", "Browse Files"),

    QuickAction("More Tools", "Advanced")
)

// ============================================================
// HOME SCREEN
//
// This composable only controls screen structure.
//
// Every major UI section will be created
// in a separate private composable.
//
// This makes the code easy to maintain.
// ============================================================

@Composable
fun HomeScreen() {

    Scaffold(

        containerColor = BackgroundColor,

        bottomBar = {

            // Bottom Navigation
            // Coming in next parts.
        }

    ) { padding ->

        LazyColumn(

            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundColor)
                .padding(padding)
                .navigationBarsPadding(),

            contentPadding = PaddingValues(16.dp),

            verticalArrangement = Arrangement.spacedBy(20.dp)

        ) {

            // ====================================================
            // TOP APP BAR
            // ====================================================

            item {

                TopBar()

            }

            // ====================================================
            // DEVICE OVERVIEW
            // ====================================================

            item {

                DeviceOverviewCard()

            }

            // ====================================================
            // QUICK ACTIONS
            // ====================================================

            item {

                QuickActionsSection()

            }

            // ====================================================
            // ACTIVITY OVERVIEW
            // ====================================================

            item {

                ActivityOverviewSection()

            }

            // ====================================================
            // RECENT DEVICES
            // ====================================================

            item {

                RecentDevicesSection()

            }


            item {
                Spacer(modifier = Modifier.height(90.dp))
            }

        }

    }

}

// ============================================================
// TEMP PLACEHOLDERS
//
// These placeholders will be replaced
// one by one in the next parts.
// ============================================================

// ============================================================
// TOP BAR
//
// Inspired by the approved DreamRAT reference design.
//
// Future:
// Menu button,
// Notification,
// Logo click,
// Drawer Navigation.
//
// ============================================================

@Composable
private fun TopBar() {

    Row(

        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),

        verticalAlignment = Alignment.CenterVertically

    ) {

        // =============================
        // MENU BUTTON
        // =============================

        Card(

            shape = CircleShape,

            colors = CardDefaults.cardColors(
                containerColor = CardColor
            )

        ) {

            IconButton(
                onClick = { }
            ) {

                Icon(
                    imageVector = Icons.Outlined.Menu,
                    contentDescription = null,
                    tint = TextWhite
                )

            }

        }

        Spacer(modifier = Modifier.width(12.dp))

        // =============================
        // LOGO
        // =============================

        Image(

            painter = painterResource(id = R.drawable.splash_image),

            contentDescription = null,

            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape),

            contentScale = ContentScale.Crop

        )

        Spacer(modifier = Modifier.width(12.dp))

        // =============================
        // TITLE
        // =============================

        Column(

            modifier = Modifier.weight(1f)

        ) {

            Text(

                text = "DreamRAT",

                color = PrimaryRed,

                fontSize = 22.sp,

                fontWeight = FontWeight.Bold

            )

            Text(

                text = "Mobile Control Center",

                color = TextGray,

                fontSize = 12.sp

            )

        }

        // =============================
        // NOTIFICATION
        // =============================

        Card(

            shape = CircleShape,

            colors = CardDefaults.cardColors(
                containerColor = CardColor
            )

        ) {

            IconButton(
                onClick = { }
            ) {

                Icon(

                    imageVector = Icons.Outlined.Notifications,

                    contentDescription = null,

                    tint = TextWhite

                )

            }

        }

    }

}

@Composable
private fun DeviceOverviewCard() {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .background(CardColor)
    )

}

@Composable
private fun QuickActionsSection() {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .background(CardColor)
    )

}

@Composable
private fun ActivityOverviewSection() {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .background(CardColor)
    )

}

@Composable
private fun RecentDevicesSection() {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .background(CardColor)
    )

}

// ============================================================
// PREVIEW
// ============================================================

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {

    HomeScreen()
}