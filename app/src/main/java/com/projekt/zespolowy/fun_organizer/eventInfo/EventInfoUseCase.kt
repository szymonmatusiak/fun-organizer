package com.projekt.zespolowy.fun_organizer.eventInfo

import com.projekt.zespolowy.fun_organizer.utils.ApiProvider

class EventInfoUseCase(private val apiProvider: ApiProvider) {
    fun getSingleEvent(eventID : Int) = apiProvider.getSingleEvent(eventID)
}