package com.projekt.zespolowy.fun_organizer.newEvent



data class EventNeedsModel(
    val description: String,
    val enough: Boolean,
    val id: Int,
    val itemSet: List<Any>,
    val name: String
)