package com.projekt.zespolowy.fun_organizer.eventEdit

import com.projekt.zespolowy.fun_organizer.utils.ApiProvider

/**
 * Created by viktor on 23.05.2018.
 */

class EventEditUseCase(private val apiProvider: ApiProvider) {
    fun getSingleEvent(eventID: Int) = apiProvider.getEventForEdit(eventID)
    fun putEventToDatabase(eventID: Int, event: EventModel) = apiProvider.putEventToDatabase(eventID, event)
}