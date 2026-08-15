package com.example.dreamrat.features.home.quick_action

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Chat
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// ============================================================
// COLORS - CONSISTENT WITH HOMESCREEN
// ============================================================
private val CardColor = Color(0xFF0A0A0A)
private val PrimaryRed = Color(0xFFFF0000)
private val TextWhite = Color.White
private val TextGray = Color(0xFF999999)

@Composable
fun QuickAction() {
    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(6.dp)
                    .clip(CircleShape)
                    .background(PrimaryRed)
            )
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
                ActionCard(Icons.AutoMirrored.Outlined.Chat, "Remote Chat", "Start Chat")
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
            .height(82.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = CardColor),
        border = BorderStroke(0.5.dp, Color.White.copy(alpha = 0.08f))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = PrimaryRed,
                modifier = Modifier.size(18.dp)
            )
            
            Spacer(modifier = Modifier.height(4.dp))
            
            Text(
                text = title,
                color = TextWhite,
                fontSize = 8.5.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                lineHeight = 10.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = subtitle,
                color = TextGray,
                fontSize = 7.sp,
                textAlign = TextAlign.Center,
                lineHeight = 9.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
