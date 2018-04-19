package com.projekt.zespolowy.fun_organizer.eventInfo

import com.projekt.zespolowy.fun_organizer.base.BasePresenter
import com.projekt.zespolowy.fun_organizer.utils.SchedulersProvider

class EventInfoPresenter(
        private val eventInfoUseCase: EventInfoUseCase,
        private val schedulersProvider: SchedulersProvider) : BasePresenter<EventInfoView>() {

    fun onStart(eventInfoView: EventInfoView) {
        attachView(eventInfoView)
    }

    fun onStop() {
        detachView(false)
    }

    fun getEventInfo(eventID : Int) {
        eventInfoUseCase
                .getSingleEvent(eventID)
                .subscribeOn(schedulersProvider.backgroundThread())
                .observeOn(schedulersProvider.mainThread())
                .subscribe(
                        {
                            view?.setEvnetInfo(it)
                        },
                        {
                            println(it.toString())
                        }
                )
    }

}