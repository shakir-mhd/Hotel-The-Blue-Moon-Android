package com.bluemoon.resort.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Hotel
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.bluemoon.resort.data.BookingRecord
import com.bluemoon.resort.data.HotelRepository
import com.bluemoon.resort.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingDialog(
    initialRoomId: String = "single",
    initialStayType: String = "night",
    onDismiss: () -> Unit,
    onBookingConfirmed: (BookingRecord) -> Unit = {}
) {
    var step by rememberSaveable { mutableIntStateOf(1) }
    var selectedRoomId by rememberSaveable { mutableStateOf(initialRoomId) }
    var selectedStayType by rememberSaveable { mutableStateOf(initialStayType) }
    var checkInDate by rememberSaveable { mutableStateOf("2026-11-15") }
    var checkOutDate by rememberSaveable { mutableStateOf("2026-11-18") }
    var nightsCount by rememberSaveable { mutableIntStateOf(3) }
    var adultsCount by rememberSaveable { mutableIntStateOf(2) }
    var childrenCount by rememberSaveable { mutableIntStateOf(0) }

    var firstName by rememberSaveable { mutableStateOf("") }
    var lastName by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var phone by rememberSaveable { mutableStateOf("") }
    var specialRequests by rememberSaveable { mutableStateOf("") }
    var validationError by rememberSaveable { mutableStateOf<String?>(null) }
    var confirmedBooking by remember { mutableStateOf<BookingRecord?>(null) }

    val currentRoom = HotelRepository.getRoom(selectedRoomId)
    val rate = if (selectedStayType == "night") currentRoom.priceNight else currentRoom.priceDay
    val subtotal = rate * nightsCount.coerceAtLeast(1)
    val tax = (subtotal * 0.18).toLong()
    val total = subtotal + tax

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
                // Header Bar
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(ResortBlue950)
                        .padding(horizontal = 20.dp, vertical = 16.dp)
                ) {
                    Column(modifier = Modifier.align(Alignment.CenterStart)) {
                        Text(
                            text = "Hotel The Blue Moon",
                            color = White,
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                        Text(
                            text = when (step) {
                                1 -> "Step 1 of 3: Dates & Accommodations"
                                2 -> "Step 2 of 3: Guest Information"
                                else -> "Step 3 of 3: Reservation Confirmed"
                            },
                            color = ResortGold300,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                    IconButton(
                        onClick = onDismiss,
                        modifier = Modifier.align(Alignment.CenterEnd)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            tint = White
                        )
                    }
                }

                // Step Contents
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    when (step) {
                        1 -> {
                            // Room Selection
                            Text(
                                text = "Select Room Type",
                                style = MaterialTheme.typography.titleSmall,
                                color = Slate800,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(8.dp))

                            var roomMenuExpanded by remember { mutableStateOf(false) }
                            ExposedDropdownMenuBox(
                                expanded = roomMenuExpanded,
                                onExpandedChange = { roomMenuExpanded = !roomMenuExpanded },
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                OutlinedTextField(
                                    value = "${currentRoom.name} (${HotelRepository.formatINR(if (selectedStayType == "night") currentRoom.priceNight else currentRoom.priceDay)}/${if (selectedStayType == "night") "night" else "day"})",
                                    onValueChange = {},
                                    readOnly = true,
                                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = roomMenuExpanded) },
                                    leadingIcon = { Icon(Icons.Default.Hotel, contentDescription = null, tint = ResortGold500) },
                                    modifier = Modifier
                                        .menuAnchor(androidx.compose.material3.ExposedDropdownMenuAnchorType.PrimaryNotEditable)
                                        .fillMaxWidth(),
                                    shape = RoundedCornerShape(8.dp),
                                    colors = OutlinedTextFieldDefaults.colors(
                                        focusedBorderColor = ResortGold500,
                                        unfocusedBorderColor = Slate200
                                    )
                                )
                                ExposedDropdownMenu(
                                    expanded = roomMenuExpanded,
                                    onDismissRequest = { roomMenuExpanded = false }
                                ) {
                                    HotelRepository.rooms.forEach { room ->
                                        DropdownMenuItem(
                                            text = {
                                                Column {
                                                    Text(room.name, fontWeight = FontWeight.Bold)
                                                    Text(
                                                        "Night: ${HotelRepository.formatINR(room.priceNight)} • Full Day: ${HotelRepository.formatINR(room.priceDay)}",
                                                        fontSize = 12.sp,
                                                        color = Slate600
                                                    )
                                                }
                                            },
                                            onClick = {
                                                selectedRoomId = room.id
                                                roomMenuExpanded = false
                                            }
                                        )
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            // Stay Type Duration Selector
                            Text(
                                text = "Stay Duration Package",
                                style = MaterialTheme.typography.titleSmall,
                                color = Slate800,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(ResortBlue50)
                                    .padding(4.dp),
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .clip(RoundedCornerShape(6.dp))
                                        .background(if (selectedStayType == "night") ResortBlue950 else Color.Transparent)
                                        .clickable { selectedStayType = "night" }
                                        .padding(vertical = 10.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "ONE NIGHT STAY",
                                        color = if (selectedStayType == "night") White else Slate700,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 11.sp
                                    )
                                }
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .clip(RoundedCornerShape(6.dp))
                                        .background(if (selectedStayType == "day") ResortGold500 else Color.Transparent)
                                        .clickable { selectedStayType = "day" }
                                        .padding(vertical = 10.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "FULL DAY (24H FLEX)",
                                        color = if (selectedStayType == "day") White else Slate700,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 11.sp
                                    )
                                }
                            }
                            Text(
                                text = if (selectedStayType == "night")
                                    "Standard Stay: Check-in from 2:00 PM, Check-out by 11:00 AM."
                                else
                                    "Ultimate Stay: Dynamic 24-hour cycle from your check-in time.",
                                color = Slate600,
                                fontSize = 11.sp,
                                modifier = Modifier.padding(top = 4.dp, start = 2.dp)
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            // Dates & Nights
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                OutlinedTextField(
                                    value = checkInDate,
                                    onValueChange = { checkInDate = it },
                                    label = { Text("Check-In Date") },
                                    leadingIcon = { Icon(Icons.Default.CalendarMonth, contentDescription = null, tint = ResortGold500) },
                                    modifier = Modifier.weight(1f),
                                    shape = RoundedCornerShape(8.dp),
                                    singleLine = true
                                )
                                OutlinedTextField(
                                    value = checkOutDate,
                                    onValueChange = { checkOutDate = it },
                                    label = { Text("Check-Out Date") },
                                    leadingIcon = { Icon(Icons.Default.CalendarMonth, contentDescription = null, tint = ResortGold500) },
                                    modifier = Modifier.weight(1f),
                                    shape = RoundedCornerShape(8.dp),
                                    singleLine = true
                                )
                            }

                            Spacer(modifier = Modifier.height(12.dp))

                            // Nights count adjustment
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(ResortGold50, RoundedCornerShape(8.dp))
                                    .padding(horizontal = 14.dp, vertical = 8.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(Icons.Default.Schedule, contentDescription = null, tint = ResortGold500, modifier = Modifier.size(18.dp))
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text("Number of Nights/Cycles", fontSize = 13.sp, fontWeight = FontWeight.Medium, color = Slate800)
                                }
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    OutlinedButton(
                                        onClick = { if (nightsCount > 1) nightsCount-- },
                                        shape = CircleShape,
                                        modifier = Modifier.size(32.dp),
                                        contentPadding = androidx.compose.foundation.layout.PaddingValues(0.dp)
                                    ) { Text("-", fontSize = 16.sp, fontWeight = FontWeight.Bold) }
                                    Text(
                                        text = "$nightsCount",
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(horizontal = 12.dp),
                                        color = ResortBlue950
                                    )
                                    OutlinedButton(
                                        onClick = { if (nightsCount < 30) nightsCount++ },
                                        shape = CircleShape,
                                        modifier = Modifier.size(32.dp),
                                        contentPadding = androidx.compose.foundation.layout.PaddingValues(0.dp)
                                    ) { Text("+", fontSize = 16.sp, fontWeight = FontWeight.Bold) }
                                }
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            // Pricing Breakdown Box
                            Card(
                                shape = RoundedCornerShape(10.dp),
                                colors = CardDefaults.cardColors(containerColor = ResortBlue50),
                                border = androidx.compose.foundation.BorderStroke(1.dp, Slate200),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Column(modifier = Modifier.padding(14.dp)) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Text("Duration:", fontSize = 13.sp, color = Slate600)
                                        Text("$nightsCount ${if (selectedStayType == "night") "Nights" else "Full Days"}", fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = Slate800)
                                    }
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Text("Subtotal (${HotelRepository.formatINR(rate)} x $nightsCount):", fontSize = 13.sp, color = Slate600)
                                        Text(HotelRepository.formatINR(subtotal), fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = Slate800)
                                    }
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Text("Taxes & Resort Fees (18%):", fontSize = 13.sp, color = Slate600)
                                        Text(HotelRepository.formatINR(tax), fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = Slate800)
                                    }
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 8.dp)
                                            .height(1.dp)
                                            .background(Slate200)
                                    )
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text("Total Amount:", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Slate900)
                                        Text(
                                            HotelRepository.formatINR(total),
                                            fontSize = 18.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = ResortGold500
                                        )
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(20.dp))

                            Button(
                                onClick = { step = 2 },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(48.dp),
                                shape = RoundedCornerShape(8.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = ResortBlue950)
                            ) {
                                Text(
                                    "NEXT: GUEST DETAILS →",
                                    fontWeight = FontWeight.Bold,
                                    letterSpacing = 1.sp
                                )
                            }
                        }

                        2 -> {
                            // Guest Details Form
                            Text(
                                text = "Guest Contact Information",
                                style = MaterialTheme.typography.titleMedium,
                                color = Slate800,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(12.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                OutlinedTextField(
                                    value = firstName,
                                    onValueChange = { firstName = it },
                                    label = { Text("First Name *") },
                                    modifier = Modifier.weight(1f),
                                    shape = RoundedCornerShape(8.dp),
                                    singleLine = true
                                )
                                OutlinedTextField(
                                    value = lastName,
                                    onValueChange = { lastName = it },
                                    label = { Text("Last Name *") },
                                    modifier = Modifier.weight(1f),
                                    shape = RoundedCornerShape(8.dp),
                                    singleLine = true
                                )
                            }

                            Spacer(modifier = Modifier.height(10.dp))

                            OutlinedTextField(
                                value = email,
                                onValueChange = { email = it },
                                label = { Text("Email Address *") },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(8.dp),
                                singleLine = true
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            OutlinedTextField(
                                value = phone,
                                onValueChange = { phone = it },
                                label = { Text("Phone / WhatsApp *") },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(8.dp),
                                singleLine = true
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            OutlinedTextField(
                                value = specialRequests,
                                onValueChange = { specialRequests = it },
                                label = { Text("Special Requests (Optional)") },
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(8.dp),
                                minLines = 2,
                                maxLines = 3
                            )

                            AnimatedVisibility(visible = validationError != null) {
                                Text(
                                    text = validationError ?: "",
                                    color = MaterialTheme.colorScheme.error,
                                    fontSize = 12.sp,
                                    modifier = Modifier.padding(top = 8.dp)
                                )
                            }

                            Spacer(modifier = Modifier.height(20.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                OutlinedButton(
                                    onClick = { step = 1 },
                                    modifier = Modifier
                                        .weight(1f)
                                        .height(48.dp),
                                    shape = RoundedCornerShape(8.dp)
                                ) {
                                    Text("← BACK")
                                }
                                Button(
                                    onClick = {
                                        if (firstName.isBlank() || lastName.isBlank() || email.isBlank() || phone.isBlank()) {
                                            validationError = "Please fill in all required fields (*)."
                                        } else {
                                            validationError = null
                                            val reference = HotelRepository.generateBookingReference()
                                            val record = BookingRecord(
                                                reference = reference,
                                                roomType = selectedRoomId,
                                                roomName = currentRoom.name,
                                                stayType = selectedStayType,
                                                checkInDate = checkInDate,
                                                checkOutDate = checkOutDate,
                                                adults = adultsCount,
                                                children = childrenCount,
                                                firstName = firstName,
                                                lastName = lastName,
                                                email = email,
                                                phone = phone,
                                                specialRequests = specialRequests,
                                                nights = nightsCount,
                                                subtotal = subtotal,
                                                tax = tax,
                                                total = total
                                            )
                                            confirmedBooking = record
                                            onBookingConfirmed(record)
                                            step = 3
                                        }
                                    },
                                    modifier = Modifier
                                        .weight(1.5f)
                                        .height(48.dp),
                                    shape = RoundedCornerShape(8.dp),
                                    colors = ButtonDefaults.buttonColors(containerColor = ResortGold500)
                                ) {
                                    Text("CONFIRM RESERVATION", fontWeight = FontWeight.Bold, letterSpacing = 0.5.sp)
                                }
                            }
                        }

                        3 -> {
                            // Confirmation View
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 12.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(64.dp)
                                        .background(SuccessGreen.copy(alpha = 0.15f), CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Check,
                                        contentDescription = "Success",
                                        tint = SuccessGreen,
                                        modifier = Modifier.size(36.dp)
                                    )
                                }

                                Spacer(modifier = Modifier.height(16.dp))

                                Text(
                                    text = "Reservation Confirmed!",
                                    fontFamily = FontFamily.Serif,
                                    fontSize = 22.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Slate900
                                )

                                Spacer(modifier = Modifier.height(6.dp))

                                Text(
                                    text = "Your booking reference number is:",
                                    fontSize = 13.sp,
                                    color = Slate600
                                )

                                Spacer(modifier = Modifier.height(10.dp))

                                Box(
                                    modifier = Modifier
                                        .background(ResortGold50, RoundedCornerShape(8.dp))
                                        .border(1.dp, ResortGold400, RoundedCornerShape(8.dp))
                                        .padding(horizontal = 24.dp, vertical = 10.dp)
                                ) {
                                    Text(
                                        text = confirmedBooking?.reference ?: "BMN-2026",
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold,
                                        fontFamily = FontFamily.Monospace,
                                        color = ResortGold500
                                    )
                                }

                                Spacer(modifier = Modifier.height(16.dp))

                                Card(
                                    modifier = Modifier.fillMaxWidth(),
                                    colors = CardDefaults.cardColors(containerColor = ResortBlue50),
                                    shape = RoundedCornerShape(10.dp)
                                ) {
                                    Column(modifier = Modifier.padding(14.dp)) {
                                        Text(
                                            text = "Booking Summary",
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 14.sp,
                                            color = ResortBlue950
                                        )
                                        Spacer(modifier = Modifier.height(6.dp))
                                        Text("Guest: $firstName $lastName", fontSize = 12.sp, color = Slate700)
                                        Text("Room: ${currentRoom.name} (${if (selectedStayType == "night") "One Night Plan" else "24H Full Day Flex Plan"})", fontSize = 12.sp, color = Slate700)
                                        Text("Dates: $checkInDate to $checkOutDate ($nightsCount Nights)", fontSize = 12.sp, color = Slate700)
                                        Text("Total Amount: ${HotelRepository.formatINR(total)}", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = ResortGold500)
                                    }
                                }

                                Spacer(modifier = Modifier.height(16.dp))

                                Text(
                                    text = "We have recorded your reservation. Our reception team will reach out via email ($email) or phone ($phone) shortly.",
                                    fontSize = 12.sp,
                                    color = Slate600,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.padding(horizontal = 12.dp)
                                )

                                Spacer(modifier = Modifier.height(20.dp))

                                Button(
                                    onClick = onDismiss,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(48.dp),
                                    shape = RoundedCornerShape(8.dp),
                                    colors = ButtonDefaults.buttonColors(containerColor = ResortBlue950)
                                ) {
                                    Text("DONE", fontWeight = FontWeight.Bold, letterSpacing = 1.sp)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
