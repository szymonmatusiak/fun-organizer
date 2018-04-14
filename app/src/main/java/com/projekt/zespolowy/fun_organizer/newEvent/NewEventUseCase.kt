package com.projekt.zespolowy.fun_organizer.newEvent

import com.projekt.zespolowy.fun_organizer.utils.ApiProvider

/**
 * Created by viktor on 05.04.2018.
 */

class NewEventUseCase(private val apiProvider: ApiProvider) {
    fun postEventToDatabase(event: EventModel) = apiProvider.postEventToDatabase(event)
}