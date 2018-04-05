package com.projekt.zespolowy.fun_organizer.eventList

import com.projekt.zespolowy.fun_organizer.utils.ApiProvider

class EventListUseCase(private val apiProvider: ApiProvider) {
    //tutaj będzie(?) pobranie listy z serwera
    fun getEventList() = apiProvider.getEventList()
    //fun login(eventCredentials: Login) = apiProvider.login(eventCredentials)
}