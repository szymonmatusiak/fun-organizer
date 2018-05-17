package com.projekt.zespolowy.fun_organizer.eventGuests

import com.projekt.zespolowy.fun_organizer.base.BasePresenter
import com.projekt.zespolowy.fun_organizer.eventInfo.EventInfoView
import com.projekt.zespolowy.fun_organizer.utils.SchedulersProvider

class EventGuestsPresenter(
        private val eventGuestsUseCase: EventGuestsUseCase,
        private val schedulersProvider: SchedulersProvider) : BasePresenter<EventGuestsView>() {

    fun onStart(eventGuestsView: EventGuestsView) {
        attachView(eventGuestsView)
    }

    fun onStop() {
        detachView(false)
    }
}