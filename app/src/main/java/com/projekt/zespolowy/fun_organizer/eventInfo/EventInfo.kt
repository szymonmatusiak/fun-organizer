package com.projekt.zespolowy.fun_organizer.eventInfo

data class EventInfo(
    val id: Int,
    val name: String,
    val date: String,
    val placeInfo: String,
    val place: String,
    val address: String,
    val latitude: Double,
    val longitude: Double,
    val description: String,
    val needs: List<Need>
)