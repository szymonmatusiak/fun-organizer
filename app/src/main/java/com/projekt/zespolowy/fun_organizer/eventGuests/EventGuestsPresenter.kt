package com.projekt.zespolowy.fun_organizer.eventGuests

import com.projekt.zespolowy.fun_organizer.base.BasePresenter
import com.projekt.zespolowy.fun_organizer.utils.SchedulersProvider

class EventGuestsPresenter(
        private val eventItemsUseCase: EventGuestsUseCase,
        private val schedulersProvider: SchedulersProvider) : BasePresenter<EventGuestsView>() {

    fun onStart(eventItemsView: EventGuestsView) {
        attachView(eventItemsView)
    }

    fun onStop() {
        detachView(false)
    }
}