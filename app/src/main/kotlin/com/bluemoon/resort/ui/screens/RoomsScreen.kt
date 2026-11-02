package com.bluemoon.resort.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bluemoon.resort.R
import com.bluemoon.resort.data.HotelRepository
import com.bluemoon.resort.data.RoomModel
import com.bluemoon.resort.ui.theme.*

@Composable
fun RoomsScreen(
    onNavigateToPricing: (roomId: String) -> Unit,
    onOpenBooking: (roomId: String) -> Unit
) {
    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val columns = if (maxWidth >= 700.dp) 2 else 1

        LazyVerticalGrid(
            columns = GridCells.Fixed(columns),
            modifier = Modifier
                .fillMaxSize()
                .background(ResortBlue50),
            contentPadding = PaddingValues(bottom = 32.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Header Item across all spans
            item(span = { GridItemSpan(columns) }) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(260.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.about_room),
                        contentDescription = "Rooms Header",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.matchParentSize()
                    )
                    Box(
                        modifier = Modifier
                            .matchParentSize()
                            .background(
                                Brush.verticalGradient(
                                    listOf(
                                        ResortBlue950.copy(alpha = 0.6f),
                                        ResortBlue950.copy(alpha = 0.9f)
                                    )
                                )
                            )
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "ACCOMMODATION OPTIONS",
                            color = ResortGold300,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 2.sp
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "Rooms & Accommodation",
                            fontFamily = FontFamily.Serif,
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Bold,
                            color = White
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "If you are looking for a home away from home, just walk in here. You can get both, an incredible and relaxing experience while you are on a holiday.",
                            fontSize = 12.sp,
                            color = White.copy(alpha = 0.85f),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                    }
                }
            }

            // Room Cards
            items(HotelRepository.rooms) { room ->
                RoomCardItem(
                    room = room,
                    onNavigateToPricing = { onNavigateToPricing(room.id) },
                    onOpenBooking = { onOpenBooking(room.id) },
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }
    }
}

@Composable
private fun RoomCardItem(
    room: RoomModel,
    onNavigateToPricing: () -> Unit,
    onOpenBooking: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column {
            Box(modifier = Modifier.fillMaxWidth().height(200.dp)) {
                Image(
                    painter = painterResource(id = room.imageRes),
                    contentDescription = room.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.matchParentSize()
                )
                if (room.isLuxuryChoice) {
                    Box(
                        modifier = Modifier
                            .padding(12.dp)
                            .background(ResortGold500, RoundedCornerShape(20.dp))
                            .padding(horizontal = 12.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = "LUXURY CHOICE",
                            color = White,
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.sp
                        )
                    }
                }
            }

            Column(modifier = Modifier.padding(18.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = room.name,
                        fontFamily = FontFamily.Serif,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Slate900
                    )
                    Text(
                        text = "${HotelRepository.formatINR(room.priceNight)}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = ResortGold500
                    )
                }

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = room.description,
                    fontSize = 12.sp,
                    color = Slate600,
                    lineHeight = 18.sp
                )

                Spacer(modifier = Modifier.height(14.dp))

                // Specs Container
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(ResortGold50)
                        .border(1.dp, Slate200, RoundedCornerShape(8.dp))
                        .padding(vertical = 10.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "CAPACITY", fontSize = 9.sp, color = Slate600, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(text = "${room.capacity} ${if (room.capacity == 1) "Guest" else "Guests"}", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Slate800)
                    }
                    Box(modifier = Modifier.width(1.dp).height(28.dp).background(Slate200))
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "SIZE", fontSize = 9.sp, color = Slate600, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(text = "${room.sizeSqm} m²", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Slate800)
                    }
                    Box(modifier = Modifier.width(1.dp).height(28.dp).background(Slate200))
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "BED", fontSize = 9.sp, color = Slate600, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(text = room.bedConfig, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Slate800)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(
                        onClick = onOpenBooking,
                        modifier = Modifier.weight(1f).height(44.dp),
                        shape = RoundedCornerShape(6.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = ResortBlue950)
                    ) {
                        Text("BOOK NOW", fontSize = 11.sp, fontWeight = FontWeight.Bold, letterSpacing = 0.5.sp)
                    }
                    OutlinedButton(
                        onClick = onNavigateToPricing,
                        modifier = Modifier.weight(1f).height(44.dp),
                        shape = RoundedCornerShape(6.dp)
                    ) {
                        Text("VIEW TARIFFS", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = ResortBlue950)
                    }
                }
            }
        }
    }
}
