package com.projekt.zespolowy.fun_organizer.eventItems

import com.projekt.zespolowy.fun_organizer.eventInfo.NeedNoID
import com.projekt.zespolowy.fun_organizer.utils.ApiProvider

class EventItemsUseCase(private val apiProvider: ApiProvider) {
    fun getSingleEvent(eventID: Int) = apiProvider.getSingleEvent(eventID)
    fun getAllCategoryItems(categoryID: Int) = apiProvider.getAllCategoryItems(categoryID)
    fun postItemToCategory(catID: Int, item: SingleItemSmallModel) = apiProvider.postItemToCategory(catID, item)
    fun deleteItemInCategory(itemID: Int) = apiProvider.deleteItemInCategory(itemID)
    fun editItemInCategory(itemID: Int, item: SingleItemSmallModel) = apiProvider.editItemInCategory(itemID, item)
    fun confirmCategory(groupID: Int, group: NeedNoID) = apiProvider.confirmCategory(groupID, group)
}