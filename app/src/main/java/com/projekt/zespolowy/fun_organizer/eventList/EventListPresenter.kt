package com.projekt.zespolowy.fun_organizer.eventList

import com.projekt.zespolowy.fun_organizer.base.BasePresenter
import com.projekt.zespolowy.fun_organizer.utils.SchedulersProvider

class EventListPresenter(
        private val eventListUseCase: EventListUseCase,
        private val schedulersProvider: SchedulersProvider) : BasePresenter<EventListView>() {

    lateinit var eventList :List<Event>

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
                            EventListAdapter(it)
                            view?.setEvents(it)
                        },
                        {
                            println(it.toString())
                        }
                )
    }
}