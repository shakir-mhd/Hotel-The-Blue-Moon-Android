package com.bluemoon.resort.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
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
fun PricingScreen(
    initialTargetRoom: String? = null,
    onOpenBooking: (roomId: String, stayType: String) -> Unit
) {
    var billingPeriod by rememberSaveable { mutableStateOf("night") } // "night" or "day"

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val columns = if (maxWidth >= 900.dp) 4 else if (maxWidth >= 600.dp) 2 else 1

        LazyVerticalGrid(
            columns = GridCells.Fixed(columns),
            modifier = Modifier
                .fillMaxSize()
                .background(ResortBlue50),
            contentPadding = PaddingValues(bottom = 32.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Header
            item(span = { GridItemSpan(columns) }) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(260.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.pricing_header),
                        contentDescription = "Pricing Header",
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
                            text = "EXCLUSIVE RATES",
                            color = ResortGold300,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 2.sp
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "Room Tariffs & Stay Options",
                            fontFamily = FontFamily.Serif,
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Bold,
                            color = White
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Experience absolute transparent pricing with tailored packages. Choose between a cozy night stay or maximize your luxury with a flexible 24-hour full-day experience.",
                            fontSize = 12.sp,
                            color = White.copy(alpha = 0.85f),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                    }
                }
            }

            // Duration Toggle Selector
            item(span = { GridItemSpan(columns) }) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "SELECT YOUR STAY DURATION",
                        color = ResortGold500,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(30.dp))
                            .background(White)
                            .border(1.dp, Slate200, RoundedCornerShape(30.dp))
                            .padding(4.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(26.dp))
                                .background(if (billingPeriod == "night") ResortBlue950 else Color.Transparent)
                                .clickable { billingPeriod = "night" }
                                .padding(horizontal = 20.dp, vertical = 10.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "ONE NIGHT",
                                color = if (billingPeriod == "night") White else Slate700,
                                fontWeight = FontWeight.Bold,
                                fontSize = 11.sp
                            )
                        }
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(26.dp))
                                .background(if (billingPeriod == "day") ResortGold500 else Color.Transparent)
                                .clickable { billingPeriod = "day" }
                                .padding(horizontal = 20.dp, vertical = 10.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "FULL DAY (24H FLEX)",
                                color = if (billingPeriod == "day") White else Slate700,
                                fontWeight = FontWeight.Bold,
                                fontSize = 11.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = if (billingPeriod == "night")
                            "Standard Stay: Check-in from 2:00 PM, Check-out by 11:00 AM standard resort hours."
                        else
                            "Ultimate Stay: Dynamic 24-hour cycle. Check out exactly 24 hours after your chosen check-in time.",
                        fontSize = 11.sp,
                        color = Slate600,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
            }

            // Cards for each room
            items(HotelRepository.rooms) { room ->
                val isHighlighted = initialTargetRoom != null && room.id.equals(initialTargetRoom, ignoreCase = true)
                PricingCardItem(
                    room = room,
                    billingPeriod = billingPeriod,
                    isHighlighted = isHighlighted,
                    onBookNow = { onOpenBooking(room.id, billingPeriod) },
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }
    }
}

@Composable
private fun PricingCardItem(
    room: RoomModel,
    billingPeriod: String,
    isHighlighted: Boolean,
    onBookNow: () -> Unit,
    modifier: Modifier = Modifier
) {
    val price = if (billingPeriod == "night") room.priceNight else room.priceDay
    val perks = if (billingPeriod == "night") room.perksNight else room.perksDay

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = White),
        elevation = CardDefaults.cardElevation(defaultElevation = if (isHighlighted || room.isLuxuryChoice) 6.dp else 2.dp),
        border = if (isHighlighted || room.isLuxuryChoice) androidx.compose.foundation.BorderStroke(2.dp, ResortGold400) else null
    ) {
        Column {
            Box(modifier = Modifier.fillMaxWidth().height(160.dp)) {
                Image(
                    painter = painterResource(id = room.imageRes),
                    contentDescription = room.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.matchParentSize()
                )
                if (room.isLuxuryChoice) {
                    Box(
                        modifier = Modifier
                            .padding(10.dp)
                            .background(ResortGold500, RoundedCornerShape(16.dp))
                            .padding(horizontal = 10.dp, vertical = 3.dp)
                    ) {
                        Text(
                            text = "LUXURY CHOICE",
                            color = White,
                            fontSize = 8.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.sp
                        )
                    }
                }
            }

            Column(modifier = Modifier.padding(18.dp)) {
                Text(
                    text = room.name,
                    fontFamily = FontFamily.Serif,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Slate900
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = HotelRepository.formatINR(price),
                    fontFamily = FontFamily.Serif,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = ResortBlue950
                )
                Text(
                    text = "Per ${if (billingPeriod == "night") "Night" else "Full Day"} Stay",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = ResortGold500
                )

                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = room.description,
                    fontSize = 11.sp,
                    color = Slate600,
                    lineHeight = 16.sp
                )

                Spacer(modifier = Modifier.height(14.dp))
                Text(
                    text = "WHAT'S INCLUDED:",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = Slate800,
                    letterSpacing = 0.5.sp
                )
                Spacer(modifier = Modifier.height(6.dp))

                perks.forEach { perk ->
                    Row(
                        modifier = Modifier.padding(vertical = 3.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(16.dp)
                                .background(ResortGold50, CircleShape)
                                .border(1.dp, ResortGold300, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("✓", fontSize = 9.sp, color = ResortGold500, fontWeight = FontWeight.Bold)
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = perk,
                            fontSize = 11.sp,
                            color = Slate700,
                            lineHeight = 15.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = onBookNow,
                    modifier = Modifier.fillMaxWidth().height(44.dp),
                    shape = RoundedCornerShape(6.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (room.isLuxuryChoice) ResortGold500 else ResortBlue950
                    )
                ) {
                    Text("BOOK NOW", fontSize = 11.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.sp)
                }
            }
        }
    }
}
