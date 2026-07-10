package com.example.dreamrat.screens

// ============================================================
// DreamRAT - Parent Control Application
// File        : HomeScreen.kt
// Purpose     : Main Dashboard Screen
//
// Current Status: UI Overhaul to match reference image.
// ============================================================
import androidx.compose.foundation.Image
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Surface
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.Android
import androidx.compose.material.icons.filled.Smartphone
import androidx.compose.material.icons.outlined.*
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
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
        containerColor = BackgroundColor,
                bottomBar = {
            BottomNavigationBar()
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(
                start = 16.dp,
                top = 16.dp,
                end = 16.dp,
                bottom = 100.dp
            ),
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
// DEVICE OVERVIEW CARD
// ============================================================

@Composable
private fun DeviceOverviewCard() {
    Column {
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
                    Column(modifier = Modifier.weight(1f)) {
                        StatItem(Icons.Default.Smartphone, "Total Devices", "8", TextWhite)
                        Spacer(modifier = Modifier.height(12.dp))
                        StatItemWithDot(OnlineGreen, "Online", "3")
                        Spacer(modifier = Modifier.height(12.dp))
                        StatItemWithDot(OfflineRed, "Offline", "5")
                    }

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
                            modifier = Modifier.layout { measurable, constraints ->
                                val placeable = measurable.measure(constraints)
                                layout(placeable.width, placeable.height) {
                                    placeable.placeRelativeWithLayer(0, 0) {
                                        scaleX = 0.7f
                                        scaleY = 0.7f
                                    }
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

// ============================================================
// QUICK ACTIONS SECTION
// ============================================================

@Composable
private fun QuickActionsSection() {
    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(modifier = Modifier.size(6.dp).clip(CircleShape).background(PrimaryRed))
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "QUICK ACTIONS",
                color = PrimaryRed,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.5.sp
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                ActionCard(Icons.Outlined.Visibility, "Live Screen", "View Live")
                ActionCard(Icons.Outlined.Chat, "Remote Chat", "Start Chat")
                ActionCard(Icons.Outlined.PhotoCamera, "Camera", "Take Photo")
                ActionCard(Icons.Outlined.Mic, "Microphone", "Listen Live")
                ActionCard(Icons.Outlined.FolderOpen, "File Manager", "Browse Files")
            }
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                ActionCard(Icons.Outlined.LocationOn, "Location", "Track Device")
                ActionCard(Icons.Outlined.Lock, "Lock Device", "Lock Now")
                ActionCard(Icons.Outlined.NotificationsActive, "Send Alert", "Notify Device")
                ActionCard(Icons.Outlined.DeleteOutline, "Clear Data", "Clear All")
                ActionCard(Icons.Outlined.Settings, "More Tools", "Advanced")
            }
        }
    }
}

@Composable
private fun RowScope.ActionCard(icon: ImageVector, title: String, subtitle: String) {
    Card(
        modifier = Modifier
            .weight(1f)
            .height(85.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = CardColor),
        border = BorderStroke(0.5.dp, Color.White.copy(alpha = 0.1f))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(2.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = PrimaryRed,
                modifier = Modifier.size(20.dp)
            )
            
            Spacer(modifier = Modifier.height(4.dp))
            
            Text(
                text = title,
                color = TextWhite,
                fontSize = 9.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                lineHeight = 10.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            
            Text(
                text = subtitle,
                color = TextGray,
                fontSize = 8.sp,
                textAlign = TextAlign.Center,
                lineHeight = 9.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

// ============================================================
// ACTIVITY OVERVIEW SECTION
// ============================================================

@Composable
private fun ActivityOverviewSection() {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.size(6.dp).clip(CircleShape).background(PrimaryRed))
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "ACTIVITY OVERVIEW",
                    color = PrimaryRed,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.5.sp
                )
            }
            Text(
                text = "View All >",
                color = TextGray,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ActivityCard(Icons.Outlined.Send, "Sent", "2.45 MB", "↑ 12%", PrimaryRed)
            ActivityCard(Icons.Outlined.FileDownload, "Received", "5.32 MB", "↓ 8%", Color(0xFFFFB74D))
            ActivityCard(Icons.Outlined.Call, "Calls", "24", "↑ 5%", OnlineGreen)
            ActivityCard(Icons.Outlined.Chat, "SMS", "36", "↑ 3%", Color(0xFF4DD0E1))
        }
    }
}

@Composable
private fun RowScope.ActivityCard(
    icon: ImageVector,
    label: String,
    value: String,
    trend: String,
    iconColor: Color
) {
    Card(
        modifier = Modifier.weight(1f).height(105.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = CardColor),
        border = BorderStroke(0.5.dp, Color.White.copy(alpha = 0.1f))
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(8.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .size(28.dp)
                    .clip(CircleShape)
                    .background(iconColor.copy(alpha = 0.15f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = iconColor,
                    modifier = Modifier.size(16.dp)
                )
            }
            
            Column {
                Text(text = label, color = TextGray, fontSize = 9.sp)
                Text(
                    text = value,
                    color = TextWhite,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(text = trend, color = iconColor, fontSize = 8.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

// ============================================================
// RECENT DEVICES SECTION - EXACT MATCH TO IMAGE
// ============================================================

@Composable
private fun RecentDevicesSection() {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.size(6.dp).clip(CircleShape).background(PrimaryRed))
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "RECENT DEVICES",
                    color = PrimaryRed,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.5.sp
                )
            }
            Text(
                text = "View All >",
                color = TextGray,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            border = BorderStroke(0.5.dp, Color.White.copy(alpha = 0.05f)),
            colors = CardDefaults.cardColors(containerColor = CardColor)
        ) {
            Column {
                DeviceItem("Android Device", "SM-A528B • Android 13", "Online", "12:28 PM", true)
                HorizontalDivider(color = Color.White.copy(alpha = 0.05f))
                DeviceItem("Redmi Note 10", "M2101K7AI • Android 12", "Online", "12:10 PM", true)
                HorizontalDivider(color = Color.White.copy(alpha = 0.05f))
                DeviceItem("Samsung S20", "SM-G981B • Android 11", "Offline", "2 days ago", false)
            }
        }
    }
}

@Composable
private fun DeviceItem(
    name: String,
    model: String,
    status: String,
    time: String,
    isOnline: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Status Dot
        Box(
            modifier = Modifier
                .size(8.dp)
                .clip(CircleShape)
                .background(if (isOnline) OnlineGreen else OfflineRed)
        )
        
        Spacer(modifier = Modifier.width(12.dp))

        // Device Icon
        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .background(Color.White.copy(alpha = 0.05f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Android,
                contentDescription = null,
                tint = PrimaryRed,
                modifier = Modifier.size(20.dp)
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        // Device Info
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = name,
                color = TextWhite,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = model,
                color = TextGray,
                fontSize = 10.sp
            )
        }

        // Status and More Menu
        Column(horizontalAlignment = Alignment.End) {
            Text(
                text = status,
                color = if (isOnline) OnlineGreen else OfflineRed,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = time,
                color = TextGray,
                fontSize = 10.sp
            )
        }
        
        Spacer(modifier = Modifier.width(4.dp))
        
        Icon(
            imageVector = Icons.Outlined.MoreVert,
            contentDescription = null,
            tint = TextGray,
            modifier = Modifier.size(20.dp)
        )
    }
}

// ============================================================
// OTHER COMPONENTS
// ============================================================

@Composable
private fun StatItem(icon: ImageVector, label: String, value: String, valueColor: Color) {
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
private fun BottomNavigationBar() {

    Surface(

        modifier = Modifier.fillMaxWidth(),

        color = CardColor,

        shadowElevation = 12.dp,

        shape = RoundedCornerShape(
            topStart = 22.dp,
            topEnd = 22.dp
        )

    ) {

        Row(

            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 12.dp,
                    vertical = 14.dp
                ),

            horizontalArrangement = Arrangement.SpaceEvenly,

            verticalAlignment = Alignment.CenterVertically

        ) {

            BottomNavItem(
                icon = Icons.Default.Home,
                title = "Home",
                selected = true
            )

            BottomNavItem(
                icon = Icons.Default.PhoneAndroid,
                title = "Devices",
                selected = false
            )

            Card(

                shape = CircleShape,

                colors = CardDefaults.cardColors(
                    containerColor = PrimaryRed
                ),

                elevation = CardDefaults.cardElevation(
                    defaultElevation = 8.dp
                )

            ) {

                Image(

                    painter = painterResource(id = R.drawable.splash_image),

                    contentDescription = null,

                    modifier = Modifier
                        .padding(8.dp)
                        .size(42.dp)

                )

            }

            BottomNavItem(
                icon = Icons.Default.Description,
                title = "Logs",
                selected = false
            )

            BottomNavItem(
                icon = Icons.Default.Settings,
                title = "Settings",
                selected = false
            )

        }

    }

}
@Composable
private fun BottomNavItem(

    icon: ImageVector,

    title: String,

    selected: Boolean

) {

    Column(

        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        Icon(

            imageVector = icon,

            contentDescription = title,

            tint = if (selected) PrimaryRed else TextGray

        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(

            text = title,

            color = if (selected) PrimaryRed else TextGray,

            fontSize = 11.sp

        )

    }

}
@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}
