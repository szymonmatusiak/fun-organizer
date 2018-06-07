package com.projekt.zespolowy.fun_organizer.eventChat

import com.projekt.zespolowy.fun_organizer.utils.ApiProvider

class EventChatUseCase(private val apiProvider: ApiProvider) {
    fun getEventChat(eventID: Int) = apiProvider.getEventChat(eventID)
    fun postEventChatMessage(eventID:Int, message: EventChatModel) = apiProvider.postEventChatMessage(eventID, message)
}