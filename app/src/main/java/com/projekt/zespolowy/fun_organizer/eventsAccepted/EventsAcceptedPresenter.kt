package com.projekt.zespolowy.fun_organizer.eventList

import com.projekt.zespolowy.fun_organizer.base.BasePresenter
import com.projekt.zespolowy.fun_organizer.utils.SchedulersProvider

class EventsAcceptedPresenter(
        private val eventListUseCase: EventsAcceptedUseCase,
        private val schedulersProvider: SchedulersProvider) : BasePresenter<EventsAcceptedView>() {

    fun onStart(eventListView: EventsAcceptedView) {
        attachView(eventListView)
    }

    fun onStop() {
        detachView(false)
    }

    fun getEventsAccepted() {
        eventListUseCase
                .getEventsAccepted()
                .subscribeOn(schedulersProvider.backgroundThread())
                .observeOn(schedulersProvider.mainThread())
                .subscribe(
                        {
                            view?.setEvents(it)

                        },
                        {
                            println(it.toString())
                        }
                )
    }

    fun startNewActivity() {
        view?.startNewEventActivity()
    }

    fun onEventClicked(event: EventModel2) {
        view?.startEventInfoActivity(event)
    }
}