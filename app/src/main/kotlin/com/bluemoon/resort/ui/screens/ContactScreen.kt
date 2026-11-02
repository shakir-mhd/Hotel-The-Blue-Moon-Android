package com.bluemoon.resort.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bluemoon.resort.R
import com.bluemoon.resort.ui.theme.*

@Composable
fun ContactScreen() {
    val context = LocalContext.current
    var name by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var subject by rememberSaveable { mutableStateOf("") }
    var message by rememberSaveable { mutableStateOf("") }
    var isSubmitted by rememberSaveable { mutableStateOf(false) }
    var errorMessage by rememberSaveable { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ResortBlue50)
            .verticalScroll(rememberScrollState())
    ) {
        // Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(260.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.hero_bg),
                contentDescription = "Contact Header",
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
                    text = "GET IN TOUCH",
                    color = ResortGold300,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 2.sp
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Contact Us",
                    fontFamily = FontFamily.Serif,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = White
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Do you want to enquire about our pricing, current offers and arrangements we can help you with? Give us a call or send in your concerns through the form below.",
                    fontSize = 12.sp,
                    color = White.copy(alpha = 0.85f),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }

        // Reception Desk Cards
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Text(
                text = "OFFICE DETAILS",
                color = ResortGold500,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            )
            Text(
                text = "Reception Desk",
                fontFamily = FontFamily.Serif,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Slate900
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Office Address Card
            Card(
                modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(44.dp)
                            .background(ResortGold50, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.LocationOn, contentDescription = null, tint = ResortGold500)
                    }
                    Spacer(modifier = Modifier.width(14.dp))
                    Column {
                        Text("Reception Office", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = Slate900)
                        Text("2855 Beach Side Rd, Unawatuna,\nGalle, Sri Lanka, 80000", fontSize = 12.sp, color = Slate600)
                    }
                }
            }

            // Hours Card
            Card(
                modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(44.dp)
                            .background(ResortGold50, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.AccessTime, contentDescription = null, tint = ResortGold500)
                    }
                    Spacer(modifier = Modifier.width(14.dp))
                    Column {
                        Text("Reception Hours", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = Slate900)
                        Text("7:30 AM - 11:00 PM (Daily)", fontSize = 12.sp, color = Slate600)
                    }
                }
            }

            // Call Direct Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
                    .clickable {
                        val dialIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:+94717224755"))
                        context.startActivity(dialIntent)
                    },
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(44.dp)
                            .background(ResortGold50, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.Phone, contentDescription = null, tint = ResortGold500)
                    }
                    Spacer(modifier = Modifier.width(14.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text("Direct Phone / WhatsApp", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = Slate900)
                        Text("+94 717 224 755", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = ResortBlue950)
                    }
                    Text("TAP TO CALL", fontSize = 10.sp, color = ResortGold500, fontWeight = FontWeight.Bold)
                }
            }

            // Email Direct Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp)
                    .clickable {
                        val emailIntent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:shakirvta01@gmail.com")).apply {
                            putExtra(Intent.EXTRA_SUBJECT, "Reservation Enquiry - Hotel The Blue Moon")
                        }
                        context.startActivity(emailIntent)
                    },
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(44.dp)
                            .background(ResortGold50, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.Email, contentDescription = null, tint = ResortGold500)
                    }
                    Spacer(modifier = Modifier.width(14.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text("Official Email", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = Slate900)
                        Text("shakirvta01@gmail.com", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = ResortBlue950)
                    }
                    Text("TAP TO EMAIL", fontSize = 10.sp, color = ResortGold500, fontWeight = FontWeight.Bold)
                }
            }

            // ================= CONTACT FORM =================
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = White),
                elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    if (isSubmitted) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 24.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(60.dp)
                                    .background(SuccessGreen.copy(alpha = 0.15f), CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = null,
                                    tint = SuccessGreen,
                                    modifier = Modifier.size(36.dp)
                                )
                            }
                            Spacer(modifier = Modifier.height(14.dp))
                            Text(
                                text = "Message Sent Successfully!",
                                fontFamily = FontFamily.Serif,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = Slate900
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "Thank you for contacting Hotel The Blue Moon. Our reception desk will review your enquiry and respond to $email shortly.",
                                fontSize = 12.sp,
                                color = Slate600,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(horizontal = 16.dp)
                            )
                            Spacer(modifier = Modifier.height(18.dp))
                            OutlinedButton(
                                onClick = {
                                    isSubmitted = false
                                    name = ""
                                    email = ""
                                    subject = ""
                                    message = ""
                                },
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Text("SEND ANOTHER MESSAGE")
                            }
                        }
                    } else {
                        Text(
                            text = "Send Us a Message",
                            fontFamily = FontFamily.Serif,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Slate900
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Fill in the form below and we'll reply to your enquiry promptly.",
                            fontSize = 12.sp,
                            color = Slate600
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                        OutlinedTextField(
                            value = name,
                            onValueChange = { name = it },
                            label = { Text("Your Name *") },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(8.dp),
                            singleLine = true
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        OutlinedTextField(
                            value = email,
                            onValueChange = { email = it },
                            label = { Text("Your Email Address *") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(8.dp),
                            singleLine = true
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        OutlinedTextField(
                            value = subject,
                            onValueChange = { subject = it },
                            label = { Text("Subject *") },
                            placeholder = { Text("Reservation, Transport, Special event, etc.") },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(8.dp),
                            singleLine = true
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        OutlinedTextField(
                            value = message,
                            onValueChange = { message = it },
                            label = { Text("Message") },
                            placeholder = { Text("Write your requirements or questions here...") },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(8.dp),
                            minLines = 3,
                            maxLines = 5
                        )

                        AnimatedVisibility(visible = errorMessage != null) {
                            Text(
                                text = errorMessage ?: "",
                                color = MaterialTheme.colorScheme.error,
                                fontSize = 12.sp,
                                modifier = Modifier.padding(top = 8.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(18.dp))

                        Button(
                            onClick = {
                                if (name.isBlank() || email.isBlank() || subject.isBlank()) {
                                    errorMessage = "Please fill in all required fields (*)."
                                } else {
                                    errorMessage = null
                                    isSubmitted = true
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(46.dp),
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = ResortBlue950)
                        ) {
                            Text("SUBMIT MESSAGE", fontWeight = FontWeight.Bold, letterSpacing = 1.sp)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Resort Map Summary Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = White),
                border = androidx.compose.foundation.BorderStroke(1.dp, Slate200)
            ) {
                Column {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(ResortGold50)
                            .padding(horizontal = 16.dp, vertical = 12.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("📍 Hotel The Blue Moon", fontWeight = FontWeight.Bold, fontSize = 13.sp, color = ResortBlue950)
                            Text("Unawatuna Beach Side", fontSize = 11.sp, color = Slate600)
                        }
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp)
                            .background(Brush.verticalGradient(listOf(ResortBlue900, ResortBlue950))),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .background(White, RoundedCornerShape(10.dp))
                                .padding(horizontal = 20.dp, vertical = 12.dp)
                        ) {
                            Text("Hotel The Blue Moon", fontFamily = FontFamily.Serif, fontWeight = FontWeight.Bold, fontSize = 14.sp, color = Slate900)
                            Text("Galle, Sri Lanka ★ 4.8", fontSize = 11.sp, color = ResortGold500, fontWeight = FontWeight.Bold)
                            Text("2855 Beach Side Rd, Unawatuna", fontSize = 10.sp, color = Slate600)
                        }
                    }
                }
            }
        }
    }
}
