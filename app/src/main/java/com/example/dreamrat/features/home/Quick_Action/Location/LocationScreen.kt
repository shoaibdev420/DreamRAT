package com.example.dreamrat.features.home.quick_action.location

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dreamrat.R
import com.example.dreamrat.screens.HomeScreen
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.*

// ============================================================
// COLORS - MATCHING REFERENCE IMAGE EXACTLY
// ============================================================
private val BackgroundColor = Color(0xFF000000)
private val CardColor = Color(0xFF0A0A0A)
private val PrimaryRed = Color(0xFFFF0000)
private val TextWhite = Color.White
private val TextGray = Color(0xFF999999)
private val OnlineGreen = Color(0xFF3DDC84)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationScreen(onBackClick: () -> Unit = {}) {
    // Handle physical back button
    BackHandler {
        onBackClick()
    }

    val dummyLocation = LatLng(33.6844, 73.0479) // Islamabad center
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(dummyLocation, 15f)
    }

    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(
            initialValue = SheetValue.PartiallyExpanded
        )
    )

    val mapProperties by remember {
        mutableStateOf(
            MapProperties(
                mapStyleOptions = MapStyleOptions(
                    "[" +
                            "  { \"elementType\": \"geometry\", \"stylers\": [ { \"color\": \"#242f3e\" } ] }," +
                            "  { \"elementType\": \"labels.text.stroke\", \"stylers\": [ { \"color\": \"#242f3e\" } ] }," +
                            "  { \"elementType\": \"labels.text.fill\", \"stylers\": [ { \"color\": \"#746855\" } ] }," +
                            "  { \"featureType\": \"road\", \"elementType\": \"geometry\", \"stylers\": [ { \"color\": \"#38414e\" } ] }," +
                            "  { \"featureType\": \"road\", \"elementType\": \"geometry.stroke\", \"stylers\": [ { \"color\": \"#212a37\" } ] }," +
                            "  { \"featureType\": \"water\", \"elementType\": \"geometry\", \"stylers\": [ { \"color\": \"#17263c\" } ] }" +
                            "]"
                )
            )
        )
    }

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = 70.dp,
        sheetContainerColor = CardColor,
        sheetContentColor = TextWhite,
        sheetShape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        sheetDragHandle = {
            BottomSheetDefaults.DragHandle(
                color = Color.DarkGray,
                width = 40.dp,
                height = 4.dp
            )
        },
        topBar = {
            LocationTopBar(onBackClick)
        },
        sheetContent = {
            ChildDetailSheetContent()
        },
        containerColor = BackgroundColor
    ) { padding ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(padding)) {
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState,
                properties = mapProperties,
                uiSettings = MapUiSettings(zoomControlsEnabled = false, myLocationButtonEnabled = false)
            ) {
                // CUSTOM MARKER WITH PHOTO (Matches Reference Image)
                MarkerComposable(
                    state = rememberMarkerState(position = dummyLocation)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        // The red pin shape
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = null,
                            tint = PrimaryRed,
                            modifier = Modifier.size(60.dp)
                        )
                        // The child's photo inside the pin
                        Image(
                            painter = painterResource(id = R.drawable.splash_image),
                            contentDescription = null,
                            modifier = Modifier
                                .offset(y = (-8).dp) // Adjust to fit inside the pin head
                                .size(32.dp)
                                .clip(CircleShape)
                                .background(Color.White),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }

            LiveTrackingOverlay(modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 16.dp))

            MapActionButtons(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 16.dp, bottom = 24.dp), // Positioned above the peek area
                onRecenter = {
                    cameraPositionState.move(CameraUpdateFactory.newLatLngZoom(dummyLocation, 15f))
                }
            )
        }
    }
}

@Composable
private fun LocationTopBar(onBackClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(BackgroundColor)
            .padding(horizontal = 8.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onBackClick) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = TextWhite)
        }
        Spacer(modifier = Modifier.width(4.dp))
        Icon(Icons.Default.LocationOn, contentDescription = null, tint = PrimaryRed, modifier = Modifier.size(24.dp))
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text("Live Location", color = TextWhite, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Text("Track Your Child", color = TextGray, fontSize = 11.sp)
        }
        IconButton(onClick = { }) {
            Icon(Icons.Default.Sync, contentDescription = "Sync", tint = PrimaryRed)
        }
    }
}

@Composable
private fun LiveTrackingOverlay(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .fillMaxWidth(0.9f)
            .height(54.dp),
        shape = RoundedCornerShape(12.dp),
        color = CardColor.copy(alpha = 0.9f)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier
                .size(8.dp)
                .clip(CircleShape)
                .background(OnlineGreen))
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text("Live Tracking", color = TextWhite, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                Text("Getting latest location...", color = TextGray, fontSize = 10.sp)
            }
            Text("Just now", color = TextGray, fontSize = 10.sp)
            Spacer(modifier = Modifier.width(4.dp))
            Icon(Icons.Outlined.AccessTime, contentDescription = null, tint = TextGray, modifier = Modifier.size(12.dp))
        }
    }
}

@Composable
private fun MapActionButtons(modifier: Modifier = Modifier, onRecenter: () -> Unit) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Surface(
            modifier = Modifier
                .size(42.dp)
                .clickable { onRecenter() },
            color = CardColor,
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(1.dp, Color.White.copy(alpha = 0.05f))
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(Icons.Default.MyLocation, contentDescription = "Recenter", tint = PrimaryRed, modifier = Modifier.size(20.dp))
            }
        }
        Surface(
            color = CardColor,
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.width(42.dp),
            border = BorderStroke(1.dp, Color.White.copy(alpha = 0.05f))
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                IconButton(onClick = { }, modifier = Modifier.size(42.dp)) {
                    Icon(Icons.Default.Add, contentDescription = "Zoom In", tint = TextWhite, modifier = Modifier.size(20.dp))
                }
                HorizontalDivider(color = Color.White.copy(alpha = 0.1f), thickness = 0.5.dp)
                IconButton(onClick = { }, modifier = Modifier.size(42.dp)) {
                    Icon(Icons.Default.Remove, contentDescription = "Zoom Out", tint = TextWhite, modifier = Modifier.size(20.dp))
                }
            }
        }
    }
}

@Composable
private fun ChildDetailSheetContent() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, bottom = 32.dp)
    ) {
        // Handle is already provided by BottomSheetScaffold
        Spacer(modifier = Modifier.height(8.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.splash_image),
                contentDescription = null,
                modifier = Modifier
                    .size(52.dp)
                    .clip(CircleShape)
                    .background(Color.DarkGray),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text("Ali Hassan", color = TextWhite, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Text("SM-A528B", color = TextGray, fontSize = 12.sp)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(modifier = Modifier
                        .size(6.dp)
                        .clip(CircleShape)
                        .background(OnlineGreen))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Online", color = OnlineGreen, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                }
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.BatteryFull, contentDescription = null, tint = OnlineGreen, modifier = Modifier.size(20.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text("75%", color = TextWhite, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                Icon(Icons.Default.KeyboardArrowDown, contentDescription = null, tint = TextGray)
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = Color.Black.copy(alpha = 0.3f),
            shape = RoundedCornerShape(12.dp),
            border = BorderStroke(0.5.dp, Color.White.copy(alpha = 0.05f))
        ) {
            Row(modifier = Modifier.padding(12.dp)) {
                Icon(Icons.Outlined.LocationOn, contentDescription = null, tint = PrimaryRed, modifier = Modifier.size(20.dp))
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text("Current Location", color = PrimaryRed, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        Text("Just now", color = TextGray, fontSize = 11.sp)
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("F-10 Markaz, Islamabad,\nIslamabad Capital Territory, Pakistan", color = TextWhite, fontSize = 13.sp, lineHeight = 18.sp)
                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            BottomActionItem(Icons.Outlined.Route, "Location History", Modifier.weight(1f))
            BottomActionItem(Icons.Outlined.NearMe, "Navigate", Modifier.weight(1f))
            BottomActionItem(Icons.Outlined.Security, "Safe Zones", Modifier.weight(1f))
            BottomActionItem(Icons.Outlined.Share, "Share Location", Modifier.weight(1f))
        }
    }
}

@Composable
private fun BottomActionItem(icon: ImageVector, label: String, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier.height(78.dp),
        color = Color.Black.copy(alpha = 0.2f),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(0.5.dp, Color.White.copy(alpha = 0.05f))
    ) {
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(imageVector = icon, contentDescription = label, tint = PrimaryRed, modifier = Modifier.size(22.dp))
            Spacer(modifier = Modifier.height(6.dp))
            Text(text = label, color = TextWhite, fontSize = 9.sp, textAlign = TextAlign.Center, fontWeight = FontWeight.Medium, lineHeight = 11.sp)
        }
    }
}
@Preview(showBackground = true)
@Composable
private fun LocaationScreenPreview() {
    LocationScreen()
}