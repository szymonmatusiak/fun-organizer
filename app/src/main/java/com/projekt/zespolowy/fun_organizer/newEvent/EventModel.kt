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
        val latitude: String,
        val longitude: String,
        val description: String,
        val needs: MutableList<EventNeedsModel>
)