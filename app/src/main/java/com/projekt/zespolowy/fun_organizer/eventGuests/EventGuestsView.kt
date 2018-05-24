package com.projekt.zespolowy.fun_organizer.eventGuests

import com.projekt.zespolowy.fun_organizer.base.IBaseView
import com.projekt.zespolowy.fun_organizer.register.UserModelNoPassword

interface EventGuestsView : IBaseView {
    fun setData(it: List<UserModelNoPassword>?)
    fun startNewActivity(eventID: String)
}