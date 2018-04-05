package com.projekt.zespolowy.fun_organizer.eventList

import com.projekt.zespolowy.fun_organizer.utils.ApiProvider

class EventListUseCase(private val apiProvider: ApiProvider) {
    fun getEventList() = apiProvider.getEventList()
}