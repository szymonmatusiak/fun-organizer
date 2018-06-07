package com.projekt.zespolowy.fun_organizer.eventChat

import com.projekt.zespolowy.fun_organizer.base.IBaseView

interface EventChatView : IBaseView {
    fun setData(it: List<EventChatModel>?)
}