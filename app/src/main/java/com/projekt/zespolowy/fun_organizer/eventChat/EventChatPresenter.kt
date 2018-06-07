package com.projekt.zespolowy.fun_organizer.eventChat

import android.util.Log
import com.projekt.zespolowy.fun_organizer.base.BasePresenter
import com.projekt.zespolowy.fun_organizer.utils.SchedulersProvider

class EventChatPresenter(
        private val eventChatUseCase: EventChatUseCase,
        private val schedulersProvider: SchedulersProvider) : BasePresenter<EventChatView>(){

    fun onStart(eventChatView: EventChatView) {
        attachView(eventChatView)
    }

    fun onStop() {
        detachView(false)
    }

    fun getEventChat(eventID: Int) {
        eventChatUseCase
                .getEventChat(eventID)
                .subscribeOn(schedulersProvider.backgroundThread())
                .observeOn(schedulersProvider.mainThread())
                .subscribe(
                        {
                            view?.setData(it)
                            view?.notifyOnUpdate()
                        },
                        {
                            Log.v("kek ", "Coś nie tak :/")
                        }
                )
    }

    fun sendChatMessage(eventID: Int, message: EventChatModel) {
        eventChatUseCase
                .postEventChatMessage(eventID, message  )
                .subscribeOn(schedulersProvider.backgroundThread())
                .observeOn(schedulersProvider.mainThread())
                .subscribe(
                        {
                            getEventChat(eventID)
                            view?.notifyOnUpdate()
                        },
                        {

                        }
                )
    }
}