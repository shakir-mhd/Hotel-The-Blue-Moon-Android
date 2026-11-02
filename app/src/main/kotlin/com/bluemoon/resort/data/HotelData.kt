package com.bluemoon.resort.data

import androidx.annotation.DrawableRes
import com.bluemoon.resort.R
import java.text.NumberFormat
import java.util.Locale
import kotlin.random.Random

data class RoomModel(
    val id: String,
    val name: String,
    val description: String,
    val capacity: Int,
    val sizeSqm: Int,
    val bedConfig: String,
    val priceNight: Long,
    val priceDay: Long,
    val perksNight: List<String>,
    val perksDay: List<String>,
    @param:DrawableRes val imageRes: Int,
    val isLuxuryChoice: Boolean = false
)

data class AmenityModel(
    val id: Int,
    val title: String,
    val shortDesc: String,
    val fullDesc: String,
    @param:DrawableRes val imageRes: Int,
    val openingHours: String,
    val highlights: List<String>
)

data class GalleryItem(
    val id: Int,
    val title: String,
    @param:DrawableRes val imageRes: Int
)

data class ReviewModel(
    val id: Int,
    val authorName: String,
    val role: String,
    val comment: String,
    @param:DrawableRes val avatarRes: Int,
    val rating: Int = 5
)

data class BookingRecord(
    val reference: String,
    val roomType: String,
    val roomName: String,
    val stayType: String, // "night" or "day"
    val checkInDate: String,
    val checkOutDate: String,
    val adults: Int,
    val children: Int,
    val firstName: String,
    val lastName: String,
    val email: String,
    val phone: String,
    val specialRequests: String,
    val nights: Int,
    val subtotal: Long,
    val tax: Long,
    val total: Long
)

object HotelRepository {

    val rooms: List<RoomModel> = listOf(
        RoomModel(
            id = "single",
            name = "Single Room",
            description = "Our single rooms are complemented with modern and luxurious amenities for a wonderful stay.",
            capacity = 1,
            sizeSqm = 28,
            bedConfig = "1 Single Bed",
            priceNight = 10000L,
            priceDay = 20000L,
            perksNight = listOf(
                "Standard Check-in & Check-out",
                "Complimentary High-speed WiFi",
                "Access to Beach & Infinity Pool",
                "Welcome Drink upon Arrival"
            ),
            perksDay = listOf(
                "24-Hour Flexible Check-in/out",
                "Complimentary Gourmet Breakfast",
                "Access to Beach & Infinity Pool",
                "Mini Bar Refilled Daily",
                "10% Spa Treatment Discount"
            ),
            imageRes = R.drawable.room_single
        ),
        RoomModel(
            id = "double",
            name = "Double Room",
            description = "We offer double rooms designed with a vibrant touch to enhance your spirits for a happening holiday.",
            capacity = 2,
            sizeSqm = 38,
            bedConfig = "1 King Bed",
            priceNight = 20000L,
            priceDay = 40000L,
            perksNight = listOf(
                "Standard Check-in & Check-out",
                "Complimentary High-speed WiFi",
                "Access to Gym & Sea-view Deck",
                "Premium Tea & Coffee Setup"
            ),
            perksDay = listOf(
                "24-Hour Flexible Check-in/out",
                "Complimentary Breakfast & Dinner",
                "Access to Gym & Sea-view Deck",
                "Mini Bar Refilled Daily",
                "20% Spa & Massage Discount",
                "Priority Restaurant Booking"
            ),
            imageRes = R.drawable.room_double
        ),
        RoomModel(
            id = "suite",
            name = "Suite Room",
            description = "This is the second-highest ranked room in our hotel that offers luxury with the most advanced amenities.",
            capacity = 2,
            sizeSqm = 54,
            bedConfig = "1 Super King Bed",
            priceNight = 50000L,
            priceDay = 80000L,
            isLuxuryChoice = true,
            perksNight = listOf(
                "Panoramic Ocean-view Balcony",
                "Private Jacuzzi Bath",
                "24/7 Butler Service on Call",
                "Airport Pickup Service Included",
                "Access to VIP Executive Lounge"
            ),
            perksDay = listOf(
                "24-Hour Flexible Check-in/out",
                "All-inclusive Buffet (All Meals)",
                "Private Jacuzzi & Premium Spa Bath",
                "24/7 Dedicated Personal Butler",
                "Complimentary Luxury Airport Transfers",
                "Free Entry to Private Beach Club",
                "Champagne Bottle on Check-in"
            ),
            imageRes = R.drawable.room_suite
        ),
        RoomModel(
            id = "family",
            name = "Family Room",
            description = "Our signature expansive residency, allowing families to live like royalty.",
            capacity = 4,
            sizeSqm = 72,
            bedConfig = "2 Queen Beds",
            priceNight = 100000L,
            priceDay = 150000L,
            perksNight = listOf(
                "Interconnecting Rooms & Living Area",
                "Full Kitchenette & Dining Table",
                "Kids Play Area & Club Access",
                "Direct Beach Walkout Access"
            ),
            perksDay = listOf(
                "24-Hour Flexible Check-in/out",
                "All-Inclusive Family Buffet Plan",
                "Dedicated Private Resort Concierge",
                "Private Beach Cabana Booking",
                "Round-trip Luxury Van Transfers",
                "Complimentary Laundry Service",
                "Private Beachside BBQ Setup"
            ),
            imageRes = R.drawable.room_family
        )
    )

    val amenities: List<AmenityModel> = listOf(
        AmenityModel(
            id = 1,
            title = "An Infinite-edge Pool",
            shortDesc = "Experience the joy of swimming in our infinity-edge pool that gives you a picturesque ocean view.",
            fullDesc = "Our signature infinite-edge pool seamlessly blends with the ocean horizon, offering an unparalleled swimming experience. Relax on premium sun loungers, enjoy signature cocktails from the swim-up bar, or watch the sunset over the coast.",
            imageRes = R.drawable.amenity_pool,
            openingHours = "6:00 AM - 10:00 PM",
            highlights = listOf(
                "Complimentary poolside towels & refreshments",
                "Swim-up bar with signature cocktails & mocktails",
                "Submerged in-water loungers & sunset viewing deck",
                "Lifeguard on duty during all operational hours"
            )
        ),
        AmenityModel(
            id = 2,
            title = "An Iconic Spa",
            shortDesc = "Set forth on an exquisite wellness journey to refresh, re-energize and rejuvenate at our iconic spa.",
            fullDesc = "Escape to our world-class spa sanctuary, designed to restore balance to your body and mind. We offer bespoke therapies, custom facials, and traditional Ayurvedic massages performed by certified wellness experts.",
            imageRes = R.drawable.amenity_spa,
            openingHours = "9:00 AM - 8:00 PM",
            highlights = listOf(
                "Private steam and cedarwood sauna rooms",
                "Complimentary herbal teas & aromatherapy on arrival",
                "Certified Ayurvedic & international wellness therapists",
                "Couples treatment suites with private plunge pool"
            )
        ),
        AmenityModel(
            id = 3,
            title = "Luxurious Rooms",
            shortDesc = "Our rooms are more than just a place to stay. The relaxing ambience will keep you happy all through.",
            fullDesc = "Every room and suite at Blue Moon is crafted with elegant design, modern automated features, and premium linens to ensure a restful stay. Enjoy panoramic views and 24/7 personal service.",
            imageRes = R.drawable.amenity_rooms,
            openingHours = "24 Hours Accessible",
            highlights = listOf(
                "Private balcony with unobstructed sea or garden views",
                "Smart ambient lighting and climate control",
                "24/7 personalized room service & gourmet mini bar",
                "Plush Egyptian cotton linens and pillow menu"
            )
        ),
        AmenityModel(
            id = 4,
            title = "Private Sandy Beach",
            shortDesc = "Direct access to Unawatuna's golden sand and calm ocean waves.",
            fullDesc = "Step directly from resort lawns onto the soft sands of Unawatuna Bay. Enjoy private shaded cabanas, beachside beverage service, and complimentary water sports gear.",
            imageRes = R.drawable.amenity_beach,
            openingHours = "Sunrise to Sunset",
            highlights = listOf(
                "Private reserved beach loungers and shaded cabanas",
                "Snorkeling gear & paddleboards complimentary",
                "Beachside cocktail & fresh coconut service"
            )
        ),
        AmenityModel(
            id = 5,
            title = "High-speed WiFi Everywhere",
            shortDesc = "Stay connected with uninterrupted high-speed internet across the entire resort.",
            fullDesc = "Whether lounging poolside or relaxing on your balcony, enjoy seamless 500+ Mbps connectivity for remote work, streaming, or video calls.",
            imageRes = R.drawable.amenity_wifi,
            openingHours = "24/7 Available",
            highlights = listOf(
                "Dedicated gigabit fiber backbone throughout resort",
                "Seamless roaming across all indoor and outdoor areas",
                "Executive workspace desks with universal sockets"
            )
        ),
        AmenityModel(
            id = 6,
            title = "Gourmet Ocean Dining",
            shortDesc = "Authentic Sri Lankan delicacies and international fine dining by award-winning chefs.",
            fullDesc = "Indulge in fresh seafood catches of the day, authentic Ceylon spices, wood-fired artisan pizzas, and curated cellar wines right beside the waves.",
            imageRes = R.drawable.amenity_food,
            openingHours = "6:30 AM - 11:30 PM",
            highlights = listOf(
                "Fresh daily seafood display and live teppanyaki counter",
                "Romantic candlelight beach dining setups on request",
                "Comprehensive vegetarian, vegan and gluten-free menus"
            )
        ),
        AmenityModel(
            id = 7,
            title = "24/7 Resort Concierge",
            shortDesc = "Personalized excursion bookings, transport arrangements, and round-the-clock service.",
            fullDesc = "Our experienced local guides and dedicated concierge team ensure your holiday is effortlessly smooth, from airport pickup to whale watching tours in Mirissa.",
            imageRes = R.drawable.amenity_concierge,
            openingHours = "24 Hours Daily",
            highlights = listOf(
                "Private luxury vehicle and airport transfers",
                "Custom Galle Fort heritage and wildlife safari tours",
                "24-hour luggage assistance and currency exchange"
            )
        ),
        AmenityModel(
            id = 8,
            title = "Banquet & Conference Hall",
            shortDesc = "State-of-the-art facilities for destination weddings, corporate summits, and private galas.",
            fullDesc = "Host grand celebrations or high-profile corporate retreats in our ocean-facing air-conditioned banquet hall equipped with ultra-HD projection and professional sound systems.",
            imageRes = R.drawable.amenity_conference,
            openingHours = "By Reservation",
            highlights = listOf(
                "Capacity for up to 350 guests with multiple stage setups",
                "Ultra-HD laser projection and synchronized audio",
                "Dedicated culinary team and banquet coordination"
            )
        )
    )

    val gallery: List<GalleryItem> = listOf(
        GalleryItem(1, "Ocean View Loungers & Sun Deck", R.drawable.gallery_1),
        GalleryItem(2, "Seaside Dining & Romantic Evenings", R.drawable.gallery_2),
        GalleryItem(3, "Cliffside Terrace & Panoramic Bay", R.drawable.hero_bg),
        GalleryItem(4, "Master Ocean Suite Interior", R.drawable.room_single),
        GalleryItem(5, "Shade Sail Beach Deck", R.drawable.gallery_3),
        GalleryItem(6, "Ayurvedic Spa & Serenity Courtyard", R.drawable.about_spa)
    )

    val reviews: List<ReviewModel> = listOf(
        ReviewModel(
            id = 1,
            authorName = "EMILY HUNT",
            role = "Leisure Traveler",
            comment = "An unforgettable holiday in Unawatuna! The ocean view from our suite was mesmerizing and the staff catered to every single request with the warmest hospitality.",
            avatarRes = R.drawable.avatar_1,
            rating = 5
        ),
        ReviewModel(
            id = 2,
            authorName = "MORGAN JONATHAN",
            role = "Luxury Enthusiast",
            comment = "The infinity pool at sunset is unmatched. From authentic local seafood to the soothing spa therapies, Hotel The Blue Moon truly defines coastal luxury.",
            avatarRes = R.drawable.avatar_2,
            rating = 5
        ),
        ReviewModel(
            id = 3,
            authorName = "DAVID & SOPHIA",
            role = "Honeymooners",
            comment = "We booked the Suite Room for our honeymoon and were blown away. The private jacuzzi and candlelit beach dinner were beyond magical!",
            avatarRes = R.drawable.avatar_3,
            rating = 5
        ),
        ReviewModel(
            id = 4,
            authorName = "MICHAEL T.",
            role = "Family Vacation",
            comment = "Traveling with two kids can be stressful, but the Family Room was huge, connected, and right by the beach. The staff made everyone feel like royalty.",
            avatarRes = R.drawable.avatar_4,
            rating = 5
        )
    )

    fun formatINR(amount: Long): String {
        val locale = Locale.Builder().setLanguage("en").setRegion("IN").build()
        return "₹" + NumberFormat.getNumberInstance(locale).format(amount)
    }

    fun getRoom(id: String): RoomModel {
        return rooms.firstOrNull { it.id.equals(id, ignoreCase = true) } ?: rooms.first()
    }

    fun getAmenity(id: Int): AmenityModel? {
        return amenities.firstOrNull { it.id == id }
    }

    fun generateBookingReference(): String {
        val randomNum = Random.nextInt(100000, 999999)
        return "BMN-$randomNum-2026"
    }
}
