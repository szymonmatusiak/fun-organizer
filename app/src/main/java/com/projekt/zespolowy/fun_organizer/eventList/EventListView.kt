package com.projekt.zespolowy.fun_organizer.eventList

import com.projekt.zespolowy.fun_organizer.base.IBaseView

interface EventListView : IBaseView {
    fun setEvents(it: List<EventModel2>?)
    fun startNewEventActivity()
}