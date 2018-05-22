package com.projekt.zespolowy.fun_organizer.eventItems

import com.projekt.zespolowy.fun_organizer.base.IBaseView
import com.projekt.zespolowy.fun_organizer.eventInfo.EventInfo
import androidx.core.widget.toast
import com.projekt.zespolowy.fun_organizer.register.UserModelNoPassword

interface EventItemsView : IBaseView {
    fun setItems(it: MutableList<SingleItemModel>?)
    //fun setItemsInCategory(itemsList: MutableList<SingleItemModel>)
}