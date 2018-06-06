package com.projekt.zespolowy.fun_organizer.eventInfo

import android.widget.Toast
import com.projekt.zespolowy.fun_organizer.base.BasePresenter
import com.projekt.zespolowy.fun_organizer.utils.SchedulersProvider
import android.R.attr.duration



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

    fun getIfIsHost(eventID: Int){
        eventInfoUseCase
                .getIfIsHost(eventID)
                .subscribeOn(schedulersProvider.backgroundThread())
                .observeOn(schedulersProvider.mainThread())
                .subscribe({
                    view?.iAmHost(it)
                },{

                }
                )
    }

    fun deleteMyself(eventID: Int){
        eventInfoUseCase
                .deleteMyself(eventID)
                .subscribeOn(schedulersProvider.backgroundThread())
                .observeOn(schedulersProvider.mainThread())
                .subscribe({
                    view?.toast("You resigned from this event")
                },{
                    view?.toast("Problem with resign :(")
                }
                )
    }
}