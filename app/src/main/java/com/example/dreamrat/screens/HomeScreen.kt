package com.example.dreamrat.screens

// ============================================================
// DreamRAT - Parent Control Application
// File        : HomeScreen.kt
// Purpose     : Main Dashboard Screen
//
// Current Status: UI Overhaul to match reference image.
// ============================================================
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Smartphone
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dreamrat.R

// ============================================================
// COLORS - MATCHING REFERENCE IMAGE
// ============================================================

private val BackgroundColor = Color(0xFF000000) // Deep Black
private val CardColor = Color(0xFF080808)       // Very dark grey for cards
private val PrimaryRed = Color(0xFFFF0000)      // Pure Red for accents
private val BorderRed = Color(0xFFFF0000).copy(alpha = 0.3f)
private val OnlineGreen = Color(0xFF3DDC84)
private val OfflineRed = Color(0xFFFF5252)
private val TextWhite = Color.White
private val TextGray = Color(0xFF808080)

@Composable
fun HomeScreen() {
    Scaffold(
        containerColor = BackgroundColor
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            item { TopBar() }
            item { DeviceOverviewCard() }
            item { QuickActionsSection() }
            item { ActivityOverviewSection() }
            item { RecentDevicesSection() }
            item { Spacer(modifier = Modifier.height(20.dp)) }
        }
    }
}

@Composable
private fun TopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { }) {
            Icon(Icons.Outlined.Menu, contentDescription = null, tint = TextWhite)
        }

        Spacer(modifier = Modifier.width(8.dp))

        Image(
            painter = painterResource(id = R.drawable.splash_image),
            contentDescription = null,
            modifier = Modifier.size(40.dp).clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text("CraxsRat", color = PrimaryRed, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text("Mobile Control Center", color = TextGray, fontSize = 11.sp)
        }

        BadgedBox(
            badge = { Badge(containerColor = PrimaryRed) { Text("3", color = TextWhite) } }
        ) {
            Icon(Icons.Outlined.Notifications, contentDescription = null, tint = TextWhite)
        }
    }
}

// ============================================================
// DEVICE OVERVIEW CARD - UPDATED DESIGN
// ============================================================

@Composable
private fun DeviceOverviewCard() {
    Column {
        // Section Title with Dot
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(modifier = Modifier.size(6.dp).clip(CircleShape).background(PrimaryRed))
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "DEVICE OVERVIEW",
                color = PrimaryRed,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.5.sp
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            border = BorderStroke(0.5.dp, BorderRed),
            colors = CardDefaults.cardColors(containerColor = CardColor)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                
                Row(modifier = Modifier.fillMaxWidth()) {
                    // Left Column: Stats
                    Column(modifier = Modifier.weight(1f)) {
                        StatItem(Icons.Default.Smartphone, "Total Devices", "8", TextWhite)
                        Spacer(modifier = Modifier.height(12.dp))
                        StatItemWithDot(OnlineGreen, "Online", "3")
                        Spacer(modifier = Modifier.height(12.dp))
                        StatItemWithDot(OfflineRed, "Offline", "5")
                    }

                    // Right Side: Active Sessions Badge & Map Placeholder
                    Column(horizontalAlignment = Alignment.End) {
                        Surface(
                            shape = RoundedCornerShape(4.dp),
                            color = Color.Black,
                            border = BorderStroke(0.5.dp, Color.DarkGray)
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text("3", color = PrimaryRed, fontWeight = FontWeight.Bold)
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("Active Sessions", color = TextWhite, fontSize = 11.sp)
                            }
                        }
                        
                        // Placeholder for World Map Image
                        Spacer(modifier = Modifier.height(10.dp))
                        Box(
                            modifier = Modifier.size(150.dp, 80.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("Map View", color = TextGray.copy(alpha = 0.3f), fontSize = 10.sp)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))
                
                // Bottom Row: Status and Switch
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(modifier = Modifier.size(6.dp).clip(CircleShape).background(OnlineGreen))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Last Update: Just now", color = TextGray, fontSize = 11.sp)
                    }
                    
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Auto Refresh", color = TextGray, fontSize = 11.sp)
                        Spacer(modifier = Modifier.width(8.dp))
                        Switch(
                            checked = true,
                            onCheckedChange = {},
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = TextWhite,
                                checkedTrackColor = PrimaryRed,
                                uncheckedThumbColor = TextGray,
                                uncheckedTrackColor = Color.DarkGray
                            ),
                            modifier = Modifier.scale(0.7f)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun StatItem(icon: androidx.compose.ui.graphics.vector.ImageVector, label: String, value: String, valueColor: Color) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Surface(
            modifier = Modifier.size(32.dp),
            shape = RoundedCornerShape(6.dp),
            color = Color.Black,
            border = BorderStroke(0.5.dp, Color.DarkGray)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = PrimaryRed,
                modifier = Modifier.padding(6.dp)
            )
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(label, color = TextGray, fontSize = 10.sp)
            Text(value, color = valueColor, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
private fun StatItemWithDot(dotColor: Color, label: String, value: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(modifier = Modifier.size(32.dp), contentAlignment = Alignment.Center) {
            Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(dotColor))
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(label, color = TextGray, fontSize = 10.sp)
            Text(value, color = TextWhite, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
private fun QuickActionsSection() {
    Box(modifier = Modifier.fillMaxWidth().height(200.dp).background(CardColor)) {
        Text("Quick Actions Placeholder", color = TextGray, modifier = Modifier.align(Alignment.Center))
    }
}

@Composable
private fun ActivityOverviewSection() {
    Box(modifier = Modifier.fillMaxWidth().height(100.dp).background(CardColor)) {
        Text("Activity Overview Placeholder", color = TextGray, modifier = Modifier.align(Alignment.Center))
    }
}

@Composable
private fun RecentDevicesSection() {
    Box(modifier = Modifier.fillMaxWidth().height(200.dp).background(CardColor)) {
        Text("Recent Devices Placeholder", color = TextGray, modifier = Modifier.align(Alignment.Center))
    }
}

// Helper to scale components
private fun Modifier.scale(scale: Float): Modifier = this.then(
    Modifier.layout { measurable, constraints ->
        val placeable = measurable.measure(constraints)
        layout(placeable.width, placeable.height) {
            placeable.placeRelativeWithLayer(0, 0) {
                this.scaleX = scale
                this.scaleY = scale
            }
        }
    }
)

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}
