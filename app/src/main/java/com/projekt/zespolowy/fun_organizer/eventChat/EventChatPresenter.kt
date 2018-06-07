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
        //Log.v("fuck2", "fuck")
        eventChatUseCase
                .getEventChat(eventID)
                .subscribeOn(schedulersProvider.backgroundThread())
                .observeOn(schedulersProvider.mainThread())
                .subscribe(
                        {
                            if (it.isEmpty()) {
                                Log.v("fuck", it.toString())

                            }
                            Log.v("setData", it.toString())
                            view?.setData(it)
                        },
                        {
                            Log.v("setData", it.toString())

                        }
                )


    }
}