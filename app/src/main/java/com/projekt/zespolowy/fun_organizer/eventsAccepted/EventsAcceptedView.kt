package com.projekt.zespolowy.fun_organizer.eventList

import com.projekt.zespolowy.fun_organizer.base.IBaseView

interface EventsAcceptedView : IBaseView {
    fun setEvents(it: List<EventModel2>?)
    fun startNewEventActivity()
    fun toast(string: String)
    fun startEventInfoActivity(event: EventModel2)
}