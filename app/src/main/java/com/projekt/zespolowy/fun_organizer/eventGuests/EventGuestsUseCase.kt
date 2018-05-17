package com.projekt.zespolowy.fun_organizer.eventGuests

import com.projekt.zespolowy.fun_organizer.utils.ApiProvider

class EventGuestsUseCase(private val apiProvider: ApiProvider) {
    fun getFriendsList() = apiProvider.getFriendsList()
    fun getEventGuest(eventID: String) = apiProvider.getEventGuest(eventID)
}