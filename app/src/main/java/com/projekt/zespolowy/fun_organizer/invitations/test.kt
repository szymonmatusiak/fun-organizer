package com.projekt.zespolowy.fun_organizer.invitations


data class Invitation(
        val id: Int,
        val forUser: ForUser,
        val event: Event,
        val status: String
)

data class Event(
        val id: Int,
        val host: Host,
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

data class Host(
        val email: String,
        val name: String,
        val surname: String,
        val phoneNumber: String
)

data class Need(
        val id: Int,
        val name: String,
        val description: String,
        val enough: Boolean
)

data class ForUser(
        val email: String,
        val name: String,
        val surname: String,
        val phoneNumber: String
)