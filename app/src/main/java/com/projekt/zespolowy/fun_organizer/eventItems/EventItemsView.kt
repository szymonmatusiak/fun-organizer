package com.projekt.zespolowy.fun_organizer.eventItems

import com.projekt.zespolowy.fun_organizer.base.IBaseView
import com.projekt.zespolowy.fun_organizer.eventInfo.EventInfo
import com.projekt.zespolowy.fun_organizer.register.UserModelNoPassword

interface EventItemsView : IBaseView {
    fun setItems(it: EventInfo?)
    fun setItemsInCategory(itemsList: ArrayList<SingleItemModel>)
}