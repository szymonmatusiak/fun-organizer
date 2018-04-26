package com.projekt.zespolowy.fun_organizer.newEvent

/**
 * Created by viktor on 05.04.2018.
 */

data class EventModel(
        val name: String,
        val date: String,
        val placeInfo: String,
        val place: String,
        val address: String,
        val latitude: Double,
        val longitude: Double,
        val description: String
)