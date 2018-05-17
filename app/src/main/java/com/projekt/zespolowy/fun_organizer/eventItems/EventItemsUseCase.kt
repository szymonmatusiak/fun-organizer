package com.projekt.zespolowy.fun_organizer.eventItems

import com.projekt.zespolowy.fun_organizer.utils.ApiProvider

class EventItemsUseCase(private val apiProvider: ApiProvider) {
    fun getSingleEvent(eventID: Int) = apiProvider.getSingleEvent(eventID)
    fun getAllCategoryItems(categoryID: Int) = apiProvider.getAllCategoryItems(categoryID)
}