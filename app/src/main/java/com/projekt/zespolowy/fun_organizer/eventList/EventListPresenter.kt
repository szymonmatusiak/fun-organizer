package com.projekt.zespolowy.fun_organizer.eventList

import com.projekt.zespolowy.fun_organizer.base.BasePresenter
import com.projekt.zespolowy.fun_organizer.utils.SchedulersProvider

class EventListPresenter(
        private val eventListUseCase: EventListUseCase,
        private val schedulersProvider: SchedulersProvider) : BasePresenter<EventListView>() {

    fun onStart(eventListView: EventListView) {
        attachView(eventListView)
    }

    fun onStop() {
        detachView(false)
    }

    fun getEventList() {
        eventListUseCase
                .getEventList()
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