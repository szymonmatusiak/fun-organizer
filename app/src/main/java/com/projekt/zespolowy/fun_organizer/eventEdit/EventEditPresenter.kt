package com.projekt.zespolowy.fun_organizer.eventEdit

import com.projekt.zespolowy.fun_organizer.base.BasePresenter
import com.projekt.zespolowy.fun_organizer.utils.SchedulersProvider

/**
 * Created by viktor on 23.05.2018.
 */

class EventEditPresenter(
        private val eventEditUseCase: EventEditUseCase,
        private val schedulersProvider: SchedulersProvider) : BasePresenter<EventEditView>() {

    fun onStart(eventView: EventEditView) {
        attachView(eventView)
    }
    fun onStop() {
        detachView(false)
    }

    fun getEventModel(eventID: Int) {
        eventEditUseCase
                .getSingleEvent(eventID)
                .subscribeOn(schedulersProvider.backgroundThread())
                .observeOn(schedulersProvider.mainThread())
                .subscribe(
                        {
                            view?.setEventModel(it)
                        },
                        {
                            println(it.toString())
                        }
                )
    }

    fun putEventToDatabase(id: Int, event: EventModel) {
        eventEditUseCase
                .putEventToDatabase(id, event)
                .subscribeOn(schedulersProvider.backgroundThread())
                .observeOn(schedulersProvider.mainThread())
                .subscribe(
                        {
                            view?.killActivity()
                            //view?.toast(it.toString())
                        },
                        {
                            println(it.toString())
                        }
                )
    }
}