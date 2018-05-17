package com.projekt.zespolowy.fun_organizer.eventInfo
import com.projekt.zespolowy.fun_organizer.register.UserModelNoPassword

data class EventInfo(
        val address: String,
        val date: String,
        val description: String,
        val host: UserModelNoPassword,
        val id: Int,
        val latitude: Double,
        val longitude: Double,
        val name: String,
        val needs: List<Need>,
        val place: String,
        val placeInfo: String
)