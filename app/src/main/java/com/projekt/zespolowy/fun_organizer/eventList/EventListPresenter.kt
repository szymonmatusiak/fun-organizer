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
                            //val eventList2: List<Event> = gson.fromJson(it , Array<Event>::class.java).toList()
                            EventListAdapter(it)
                            println(it.toString())
                        },
                        {
                            println(it.toString())
                        }
                )
    }
}