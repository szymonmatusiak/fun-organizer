package com.projekt.zespolowy.fun_organizer.eventItems

import com.projekt.zespolowy.fun_organizer.base.IBaseView

interface EventItemsView : IBaseView {
    fun setItems(it: MutableList<SingleItemModel>?)
    fun myToast (s: String)
    fun notifyOnUpdate()
    //fun setItemsInCategory(itemsList: MutableList<SingleItemModel>)
}