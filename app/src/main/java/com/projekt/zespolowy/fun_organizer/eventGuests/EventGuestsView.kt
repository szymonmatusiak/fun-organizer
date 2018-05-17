package com.projekt.zespolowy.fun_organizer.eventGuests

import com.projekt.zespolowy.fun_organizer.base.IBaseView
import com.projekt.zespolowy.fun_organizer.register.UserModel

interface EventGuestsView : IBaseView {
    fun setData(it: List<UserModel>?)
    fun startNewActivity(eventID: String)
}