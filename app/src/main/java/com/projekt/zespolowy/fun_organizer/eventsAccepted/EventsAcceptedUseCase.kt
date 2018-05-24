package com.projekt.zespolowy.fun_organizer.eventList

import com.projekt.zespolowy.fun_organizer.utils.ApiProvider

class EventsAcceptedUseCase(private val apiProvider: ApiProvider) {
    fun getEventsAccepted() = apiProvider.getEventsAccepted()
}