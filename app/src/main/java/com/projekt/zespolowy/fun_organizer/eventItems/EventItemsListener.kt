package com.projekt.zespolowy.fun_organizer.eventItems

interface EventItemsListener {
    fun onEventClicked(item: SingleItemModel)
    fun onDeleteClicked(item: SingleItemModel, fieldID: Int)
}