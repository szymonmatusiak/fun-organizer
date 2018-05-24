package com.projekt.zespolowy.fun_organizer.eventEdit

/**
 * Created by viktor on 23.05.2018.
 */
data class EventNeedsModel(
        val id: Int = -1,
        val name: String = "",
        val description: String = "",
        val enough: Boolean = false
)