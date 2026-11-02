package com.bluemoon.resort.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.bluemoon.resort.ui.theme.*

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HomeScreen(
    onNavigateToRooms: () -> Unit,
    onNavigateToPricing: (roomId: String?) -> Unit,
    onNavigateToAmenities: () -> Unit,
    onOpenAmenityDetail: (Int) -> Unit,
    onOpenGallery: (Int) -> Unit,
    onOpenBooking: (roomId: String, stayType: String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        // ================= HERO BANNER =================
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(440.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.hero_bg),
                contentDescription = "Hotel The Blue Moon Resort",
                contentScale = ContentScale.Crop,
                modifier = Modifier.matchParentSize()
            )
            // Gradient Overlay
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                ResortBlue950.copy(alpha = 0.5f),
                                ResortBlue950.copy(alpha = 0.85f)
                            )
                        )
                    )
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp, vertical = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "LUXURY BEACH RESORT • UNAWATUNA, GALLE",
                    color = ResortGold300,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 2.sp,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(10.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Hotel The ",
                        fontFamily = FontFamily.Serif,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = White
                    )
                    Text(
                        text = "Blue Moon",
                        fontFamily = FontFamily.Serif,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = ResortGold300
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "If you are looking for a perfect holiday experience with memories to cherish, you are at the right place. Let's plan a reasonable stay for you.",
                    color = White.copy(alpha = 0.88f),
                    fontSize = 13.sp,
                    lineHeight = 20.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = { onOpenBooking("single", "night") },
                    colors = ButtonDefaults.buttonColors(containerColor = ResortGold500),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.height(46.dp)
                ) {
                    Text(
                        text = "BOOK YOUR STAY NOW",
                        fontWeight = FontWeight.Bold,
                        color = White,
                        fontSize = 12.sp,
                        letterSpacing = 1.sp
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                        tint = White
                    )
                }
            }
        }

        // ================= ABOUT SECTION PREVIEW =================
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(White)
                .padding(20.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp)
                    .clip(RoundedCornerShape(12.dp))
            ) {
                Image(
                    painter = painterResource(id = R.drawable.about_spa),
                    contentDescription = "About Resort Spa",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.matchParentSize()
                )
                // 10+ Years badge
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .background(ResortBlue950.copy(alpha = 0.95f), RoundedCornerShape(topEnd = 8.dp))
                        .border(
                            width = 2.dp,
                            color = ResortGold400,
                            shape = RoundedCornerShape(topEnd = 8.dp)
                        )
                        .padding(horizontal = 14.dp, vertical = 8.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "10+",
                            fontFamily = FontFamily.Serif,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = ResortGold300
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Years of Luxury\nHospitality",
                            fontSize = 10.sp,
                            color = White,
                            lineHeight = 13.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "WELCOME TO PARADISE",
                color = ResortGold500,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Experience the Life of Hotel The Blue Moon",
                fontFamily = FontFamily.Serif,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Slate900,
                lineHeight = 28.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "We will help you experience the Blue Moon life in its true sense. From living among the residents to eating authentic delicacies you are sure to walk home with memories and wonderful moments.",
                fontSize = 13.sp,
                color = Slate600,
                lineHeight = 20.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedButton(
                onClick = onNavigateToRooms,
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.height(40.dp)
            ) {
                Text(
                    text = "EXPLORE ROOMS →",
                    fontWeight = FontWeight.Bold,
                    fontSize = 11.sp,
                    color = ResortBlue950
                )
            }
        }

        // ================= FEATURED ROOMS PREVIEW =================
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(ResortBlue50)
                .padding(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "LUXURY STAYS",
                        color = ResortGold500,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )
                    Text(
                        text = "Come & Stay With Us",
                        fontFamily = FontFamily.Serif,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Slate900
                    )
                }
                OutlinedButton(
                    onClick = onNavigateToRooms,
                    shape = RoundedCornerShape(6.dp),
                    contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Text("VIEW ALL", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Show first 2 featured rooms
            HotelRepository.rooms.take(2).forEach { room ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column {
                        Image(
                            painter = painterResource(id = room.imageRes),
                            contentDescription = room.name,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(170.dp)
                        )
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = room.name,
                                    fontFamily = FontFamily.Serif,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Slate900
                                )
                                Text(
                                    text = "${HotelRepository.formatINR(room.priceNight)} / night",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp,
                                    color = ResortGold500
                                )
                            }
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = room.description,
                                fontSize = 12.sp,
                                color = Slate600,
                                maxLines = 2
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Button(
                                    onClick = { onOpenBooking(room.id, "night") },
                                    shape = RoundedCornerShape(6.dp),
                                    colors = ButtonDefaults.buttonColors(containerColor = ResortBlue950),
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Text("BOOK NOW", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                                }
                                OutlinedButton(
                                    onClick = { onNavigateToPricing(room.id) },
                                    shape = RoundedCornerShape(6.dp),
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Text("VIEW TARIFFS", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                                }
                            }
                        }
                    }
                }
            }
        }

        // ================= SIGNATURE AMENITIES PREVIEW =================
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(White)
                .padding(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "PREMIUM OFFERINGS",
                        color = ResortGold500,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )
                    Text(
                        text = "Amenities & Facilities",
                        fontFamily = FontFamily.Serif,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Slate900
                    )
                }
                OutlinedButton(
                    onClick = onNavigateToAmenities,
                    shape = RoundedCornerShape(6.dp),
                    contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Text("VIEW ALL", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            HotelRepository.amenities.take(3).forEach { amenity ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp)
                        .clickable { onOpenAmenityDetail(amenity.id) },
                    shape = RoundedCornerShape(10.dp),
                    colors = CardDefaults.cardColors(containerColor = ResortBlue50),
                    border = androidx.compose.foundation.BorderStroke(1.dp, Slate200)
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = amenity.imageRes),
                            contentDescription = amenity.title,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(72.dp)
                                .clip(RoundedCornerShape(8.dp))
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = amenity.title,
                                fontFamily = FontFamily.Serif,
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp,
                                color = Slate800
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = amenity.shortDesc,
                                fontSize = 12.sp,
                                color = Slate600,
                                maxLines = 2,
                                lineHeight = 16.sp
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Read More →",
                                color = ResortGold500,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }

        // ================= PHOTO GALLERY PREVIEW =================
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(ResortBlue50)
                .padding(20.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "CAPTURED MOMENTS",
                    color = ResortGold500,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "Our Inside Pictures",
                    fontFamily = FontFamily.Serif,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Slate900
                )
                Box(
                    modifier = Modifier
                        .padding(top = 6.dp, bottom = 16.dp)
                        .width(48.dp)
                        .height(2.dp)
                        .background(ResortGold400)
                )
            }

            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                maxItemsInEachRow = 2
            ) {
                HotelRepository.gallery.forEachIndexed { index, item ->
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .height(130.dp)
                            .clickable { onOpenGallery(index) },
                        shape = RoundedCornerShape(8.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                    ) {
                        Image(
                            painter = painterResource(id = item.imageRes),
                            contentDescription = item.title,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            }
        }

        // ================= GUEST TESTIMONIALS =================
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(White)
                .padding(20.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "GUEST EXPERIENCES",
                    color = ResortGold500,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "What Our Guests Say",
                    fontFamily = FontFamily.Serif,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Slate900
                )
                Box(
                    modifier = Modifier
                        .padding(top = 6.dp, bottom = 16.dp)
                        .width(48.dp)
                        .height(2.dp)
                        .background(ResortGold400)
                )
            }

            HotelRepository.reviews.take(2).forEach { review ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = CardDefaults.cardColors(containerColor = ResortBlue50),
                    border = androidx.compose.foundation.BorderStroke(1.dp, Slate200)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row {
                            repeat(review.rating) {
                                Icon(
                                    imageVector = Icons.Default.Star,
                                    contentDescription = null,
                                    tint = StarGold,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "\"${review.comment}\"",
                            fontSize = 12.sp,
                            color = Slate700,
                            lineHeight = 18.sp,
                            fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Image(
                                painter = painterResource(id = review.avatarRes),
                                contentDescription = review.authorName,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(38.dp)
                                    .clip(CircleShape)
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Column {
                                Text(
                                    text = review.authorName,
                                    fontFamily = FontFamily.Serif,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 13.sp,
                                    color = Slate900
                                )
                                Text(
                                    text = review.role,
                                    fontSize = 10.sp,
                                    color = Slate600
                                )
                            }
                        }
                    }
                }
            }
        }

        // ================= BOTTOM BANNER CTA =================
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(ResortBlue950)
                .padding(28.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Plan an Unforgettable Experience in\nBlue Moon Hotel !",
                    fontFamily = FontFamily.Serif,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = White,
                    textAlign = TextAlign.Center,
                    lineHeight = 24.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "We can help you fit your stay and experience within your allotted budget.",
                    fontSize = 12.sp,
                    color = White.copy(alpha = 0.75f),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { onOpenBooking("single", "night") },
                    colors = ButtonDefaults.buttonColors(containerColor = ResortGold500),
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Text(
                        text = "RESERVE A ROOM NOW",
                        fontWeight = FontWeight.Bold,
                        color = White,
                        fontSize = 11.sp,
                        letterSpacing = 1.sp
                    )
                }
            }
        }
    }
}
