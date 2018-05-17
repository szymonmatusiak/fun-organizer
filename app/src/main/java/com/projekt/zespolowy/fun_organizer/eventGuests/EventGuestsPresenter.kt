package com.projekt.zespolowy.fun_organizer.eventGuests

import android.util.Log
import com.projekt.zespolowy.fun_organizer.base.BasePresenter
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

    fun getEventGuest(eventID: String) {
        Log.v("fuck2", "fuck")

        eventGuestsUseCase
                .getEventGuest(eventID)
                .subscribeOn(schedulersProvider.backgroundThread())
                .observeOn(schedulersProvider.mainThread())
                .subscribe(
                        {
                            if (it.isEmpty()) {
                                Log.v("fuck", it.toString())

                            }
                            Log.v("setData", it.toString())
                            //view?.setData(it)
                        },
                        {
                            Log.v("setData", it.toString())

                        }
                )
    }

    fun startNewActivity(eventID: String) {
        view?.startNewActivity(eventID)
    }
}