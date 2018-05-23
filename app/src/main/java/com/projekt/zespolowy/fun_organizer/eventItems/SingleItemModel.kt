package com.projekt.zespolowy.fun_organizer.eventItems



data class SingleItemModel(
    val id: Int,
    val name: String,
    val description: String,
    val value: Int,
    val declared: Declared
)