package com.bluemoon.resort.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.bluemoon.resort.data.AmenityModel
import com.bluemoon.resort.ui.theme.ResortBlue950
import com.bluemoon.resort.ui.theme.ResortGold300
import com.bluemoon.resort.ui.theme.ResortGold400
import com.bluemoon.resort.ui.theme.ResortGold50
import com.bluemoon.resort.ui.theme.ResortGold500
import com.bluemoon.resort.ui.theme.Slate200
import com.bluemoon.resort.ui.theme.Slate600
import com.bluemoon.resort.ui.theme.Slate800
import com.bluemoon.resort.ui.theme.White

@Composable
fun AmenityDetailDialog(
    amenity: AmenityModel,
    onDismiss: () -> Unit,
    onBookExperience: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .padding(vertical = 24.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = White),
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {
                // Header Image with Close Button
                Box(modifier = Modifier.fillMaxWidth().height(220.dp)) {
                    Image(
                        painter = painterResource(id = amenity.imageRes),
                        contentDescription = amenity.title,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.matchParentSize()
                    )
                    IconButton(
                        onClick = onDismiss,
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(12.dp)
                            .background(ResortBlue950.copy(alpha = 0.65f), CircleShape)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            tint = White
                        )
                    }
                }

                // Body content
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    Text(
                        text = "FEATURE DETAILS",
                        color = ResortGold500,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = amenity.title,
                        fontFamily = FontFamily.Serif,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Slate800
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Hours Chip
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .background(ResortGold50, RoundedCornerShape(6.dp))
                            .border(1.dp, ResortGold300.copy(alpha = 0.5f), RoundedCornerShape(6.dp))
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.AccessTime,
                            contentDescription = null,
                            tint = ResortGold500,
                            modifier = Modifier.size(14.dp)
                        )
                        Text(
                            text = "  Hours: ${amenity.openingHours}",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            color = Slate800
                        )
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    Text(
                        text = amenity.fullDesc,
                        fontSize = 14.sp,
                        color = Slate600,
                        lineHeight = 22.sp
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "KEY HIGHLIGHTS",
                        color = Slate800,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.5.sp
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    amenity.highlights.forEach { highlight ->
                        Row(
                            modifier = Modifier.padding(vertical = 4.dp),
                            verticalAlignment = Alignment.Top
                        ) {
                            Text(
                                text = "✓",
                                color = ResortGold500,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                modifier = Modifier.padding(end = 8.dp)
                            )
                            Text(
                                text = highlight,
                                fontSize = 13.sp,
                                color = Slate800,
                                lineHeight = 18.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        OutlinedButton(
                            onClick = onDismiss,
                            modifier = Modifier
                                .weight(1f)
                                .height(46.dp),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text("CLOSE", fontWeight = FontWeight.Bold)
                        }
                        Button(
                            onClick = {
                                onDismiss()
                                onBookExperience()
                            },
                            modifier = Modifier
                                .weight(1.5f)
                                .height(46.dp),
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = ResortBlue950)
                        ) {
                            Text("BOOK THIS EXPERIENCE", fontWeight = FontWeight.Bold, fontSize = 11.sp)
                        }
                    }
                }
            }
        }
    }
}
