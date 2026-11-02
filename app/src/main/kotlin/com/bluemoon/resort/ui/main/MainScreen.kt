package com.bluemoon.resort.ui.main

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Hotel
import androidx.compose.material.icons.filled.Nightlight
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Pool
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation3.runtime.NavKey
import com.bluemoon.resort.data.HotelRepository
import com.bluemoon.resort.ui.components.AmenityDetailDialog
import com.bluemoon.resort.ui.components.BookingDialog
import com.bluemoon.resort.ui.components.GalleryLightboxDialog
import com.bluemoon.resort.ui.screens.AmenitiesScreen
import com.bluemoon.resort.ui.screens.ContactScreen
import com.bluemoon.resort.ui.screens.HomeScreen
import com.bluemoon.resort.ui.screens.PricingScreen
import com.bluemoon.resort.ui.screens.RoomsScreen
import com.bluemoon.resort.ui.theme.ResortBlue50
import com.bluemoon.resort.ui.theme.ResortBlue900
import com.bluemoon.resort.ui.theme.ResortBlue950
import com.bluemoon.resort.ui.theme.ResortGold300
import com.bluemoon.resort.ui.theme.ResortGold400
import com.bluemoon.resort.ui.theme.ResortGold50
import com.bluemoon.resort.ui.theme.ResortGold500
import com.bluemoon.resort.ui.theme.Slate400
import com.bluemoon.resort.ui.theme.Slate600
import com.bluemoon.resort.ui.theme.White

enum class ResortNavTab(val title: String, val icon: ImageVector) {
    HOME("Home", Icons.Default.Home),
    ROOMS("Rooms", Icons.Default.Hotel),
    PRICING("Pricing", Icons.Default.Payments),
    AMENITIES("Amenities", Icons.Default.Pool),
    CONTACT("Contact", Icons.Default.Phone)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    onItemClick: (NavKey) -> Unit = {},
    modifier: Modifier = Modifier
) {
    var selectedTab by rememberSaveable { mutableStateOf(ResortNavTab.HOME) }
    var pricingTargetRoom by rememberSaveable { mutableStateOf<String?>(null) }

    // Dialog state
    var showBookingModal by rememberSaveable { mutableStateOf(false) }
    var bookingRoomId by rememberSaveable { mutableStateOf("single") }
    var bookingStayType by rememberSaveable { mutableStateOf("night") }

    var activeAmenityDetailId by rememberSaveable { mutableStateOf<Int?>(null) }
    var activeGalleryIndex by rememberSaveable { mutableStateOf<Int?>(null) }

    BoxWithConstraints(modifier = modifier.fillMaxSize()) {
        val isExpandedScreen = maxWidth >= 600.dp

        if (isExpandedScreen) {
            // Adaptive Tablet / Foldable / Landscape layout with NavigationRail
            Row(modifier = Modifier.fillMaxSize()) {
                NavigationRail(
                    containerColor = ResortBlue950,
                    contentColor = White,
                    header = {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(vertical = 16.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(44.dp)
                                    .background(ResortGold50.copy(alpha = 0.15f), CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Nightlight,
                                    contentDescription = "Logo",
                                    tint = ResortGold400,
                                    modifier = Modifier
                                        .size(24.dp)
                                        .rotate(-15f)
                                )
                            }
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "Blue Moon",
                                fontFamily = FontFamily.Serif,
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp,
                                color = White
                            )
                        }
                    }
                ) {
                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            ResortNavTab.entries.forEach { tab ->
                                NavigationRailItem(
                                    selected = selectedTab == tab,
                                    onClick = { selectedTab = tab },
                                    icon = { Icon(tab.icon, contentDescription = tab.title) },
                                    label = { Text(tab.title, fontSize = 11.sp, fontWeight = FontWeight.Medium) },
                                    colors = NavigationRailItemDefaults.colors(
                                        selectedIconColor = ResortBlue950,
                                        selectedTextColor = ResortGold300,
                                        indicatorColor = ResortGold400,
                                        unselectedIconColor = White.copy(alpha = 0.7f),
                                        unselectedTextColor = White.copy(alpha = 0.7f)
                                    )
                                )
                            }
                        }

                        // Bottom Action Button in Rail
                        Box(modifier = Modifier.padding(bottom = 16.dp, start = 8.dp, end = 8.dp)) {
                            Button(
                                onClick = {
                                    bookingRoomId = "single"
                                    bookingStayType = "night"
                                    showBookingModal = true
                                },
                                shape = RoundedCornerShape(8.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = ResortGold500),
                                contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 8.dp, vertical = 6.dp)
                            ) {
                                Text("BOOK", fontSize = 10.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }

                // Main Content Area
                Box(modifier = Modifier.weight(1f).fillMaxHeight()) {
                    ScreenContent(
                        selectedTab = selectedTab,
                        pricingTargetRoom = pricingTargetRoom,
                        onNavigateToRooms = { selectedTab = ResortNavTab.ROOMS },
                        onNavigateToPricing = { roomId ->
                            pricingTargetRoom = roomId
                            selectedTab = ResortNavTab.PRICING
                        },
                        onNavigateToAmenities = { selectedTab = ResortNavTab.AMENITIES },
                        onOpenAmenityDetail = { activeAmenityDetailId = it },
                        onOpenGallery = { activeGalleryIndex = it },
                        onOpenBooking = { roomId, stayType ->
                            bookingRoomId = roomId
                            bookingStayType = stayType
                            showBookingModal = true
                        }
                    )
                }
            }
        } else {
            // Compact Phone Portrait layout with TopAppBar and Bottom NavigationBar
            Scaffold(
                topBar = {
                    CenterAlignedTopAppBar(
                        title = {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.Nightlight,
                                    contentDescription = null,
                                    tint = ResortGold400,
                                    modifier = Modifier
                                        .size(20.dp)
                                        .rotate(-15f)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Column {
                                    Text(
                                        text = "Hotel The Blue Moon",
                                        fontFamily = FontFamily.Serif,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 16.sp,
                                        color = White
                                    )
                                    Text(
                                        text = "LUXURY BEACH RESORT",
                                        fontSize = 8.sp,
                                        letterSpacing = 1.sp,
                                        color = ResortGold300
                                    )
                                }
                            }
                        },
                        actions = {
                            Button(
                                onClick = {
                                    bookingRoomId = "single"
                                    bookingStayType = "night"
                                    showBookingModal = true
                                },
                                shape = RoundedCornerShape(6.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = ResortGold500),
                                modifier = Modifier
                                    .padding(end = 12.dp)
                                    .height(32.dp),
                                contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 10.dp, vertical = 0.dp)
                            ) {
                                Text(
                                    text = "RESERVE",
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    letterSpacing = 0.5.sp,
                                    color = White
                                )
                            }
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = ResortBlue950
                        )
                    )
                },
                bottomBar = {
                    NavigationBar(
                        containerColor = ResortBlue950,
                        contentColor = White,
                        tonalElevation = 8.dp
                    ) {
                        ResortNavTab.entries.forEach { tab ->
                            NavigationBarItem(
                                selected = selectedTab == tab,
                                onClick = { selectedTab = tab },
                                icon = { Icon(tab.icon, contentDescription = tab.title) },
                                label = { Text(tab.title, fontSize = 10.sp, fontWeight = FontWeight.Medium) },
                                colors = NavigationBarItemDefaults.colors(
                                    selectedIconColor = ResortBlue950,
                                    selectedTextColor = ResortGold300,
                                    indicatorColor = ResortGold400,
                                    unselectedIconColor = White.copy(alpha = 0.65f),
                                    unselectedTextColor = White.copy(alpha = 0.65f)
                                )
                            )
                        }
                    }
                }
            ) { innerPadding ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    ScreenContent(
                        selectedTab = selectedTab,
                        pricingTargetRoom = pricingTargetRoom,
                        onNavigateToRooms = { selectedTab = ResortNavTab.ROOMS },
                        onNavigateToPricing = { roomId ->
                            pricingTargetRoom = roomId
                            selectedTab = ResortNavTab.PRICING
                        },
                        onNavigateToAmenities = { selectedTab = ResortNavTab.AMENITIES },
                        onOpenAmenityDetail = { activeAmenityDetailId = it },
                        onOpenGallery = { activeGalleryIndex = it },
                        onOpenBooking = { roomId, stayType ->
                            bookingRoomId = roomId
                            bookingStayType = stayType
                            showBookingModal = true
                        }
                    )
                }
            }
        }
    }

    // Modal Dialogs
    if (showBookingModal) {
        BookingDialog(
            initialRoomId = bookingRoomId,
            initialStayType = bookingStayType,
            onDismiss = { showBookingModal = false }
        )
    }

    activeAmenityDetailId?.let { amenityId ->
        HotelRepository.getAmenity(amenityId)?.let { amenity ->
            AmenityDetailDialog(
                amenity = amenity,
                onDismiss = { activeAmenityDetailId = null },
                onBookExperience = {
                    bookingRoomId = "single"
                    bookingStayType = "night"
                    showBookingModal = true
                }
            )
        }
    }

    activeGalleryIndex?.let { galleryIdx ->
        GalleryLightboxDialog(
            initialIndex = galleryIdx,
            onDismiss = { activeGalleryIndex = null }
        )
    }
}

@Composable
private fun ScreenContent(
    selectedTab: ResortNavTab,
    pricingTargetRoom: String?,
    onNavigateToRooms: () -> Unit,
    onNavigateToPricing: (roomId: String?) -> Unit,
    onNavigateToAmenities: () -> Unit,
    onOpenAmenityDetail: (Int) -> Unit,
    onOpenGallery: (Int) -> Unit,
    onOpenBooking: (roomId: String, stayType: String) -> Unit
) {
    Crossfade(targetState = selectedTab, label = "TabCrossfade") { tab ->
        when (tab) {
            ResortNavTab.HOME -> HomeScreen(
                onNavigateToRooms = onNavigateToRooms,
                onNavigateToPricing = onNavigateToPricing,
                onNavigateToAmenities = onNavigateToAmenities,
                onOpenAmenityDetail = onOpenAmenityDetail,
                onOpenGallery = onOpenGallery,
                onOpenBooking = onOpenBooking
            )
            ResortNavTab.ROOMS -> RoomsScreen(
                onNavigateToPricing = { roomId -> onNavigateToPricing(roomId) },
                onOpenBooking = { roomId -> onOpenBooking(roomId, "night") }
            )
            ResortNavTab.PRICING -> PricingScreen(
                initialTargetRoom = pricingTargetRoom,
                onOpenBooking = onOpenBooking
            )
            ResortNavTab.AMENITIES -> AmenitiesScreen(
                onOpenAmenityDetail = onOpenAmenityDetail
            )
            ResortNavTab.CONTACT -> ContactScreen()
        }
    }
}

@Preview(name = "Phone", showBackground = true)
@Preview(name = "Tablet", widthDp = 840, heightDp = 600, showBackground = true)
@Composable
fun MainScreenPreview() {
    com.bluemoon.resort.ui.theme.HotelTheBlueMoonTheme {
        MainScreen()
    }
}
