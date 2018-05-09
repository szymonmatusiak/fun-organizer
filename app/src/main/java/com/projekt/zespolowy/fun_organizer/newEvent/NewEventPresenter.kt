package com.projekt.zespolowy.fun_organizer.newEvent

import com.projekt.zespolowy.fun_organizer.base.BasePresenter
import com.projekt.zespolowy.fun_organizer.utils.SchedulersProvider

/**
 * Created by viktor on 05.04.2018.
 */

class NewEventPresenter(
private val eventUseCase: NewEventUseCase,
private val schedulersProvider: SchedulersProvider
) : BasePresenter<NewEventView>() {

    fun onStart(eventView: NewEventView) {
        attachView(eventView)
    }

    fun onStop() {
        detachView(false)
    }


    fun checkCorrectness(event: EventModel) : Boolean {
        if (event.placeInfo.trim().length <= 0 || event.name.trim().length <= 0)
            return false
        else {
            println(event.address)
            println(event.date)
            println(event.description)
            println(event.latitude)
            println(event.longitude)
            println(event.name)
            println(event.place)
            println(event.placeInfo)
            return true
        }
    }

    fun postEventToDatabase(event: EventModel): Boolean {
        if(checkCorrectness(event) == true) {
            eventUseCase
                    .postEventToDatabase(event)
                    .subscribeOn(schedulersProvider.backgroundThread())
                    .observeOn(schedulersProvider.mainThread())
                    .subscribe(
                            {
                                view?.killActivity()
                                view?.toast(it.toString())
                            },
                            {
                                view?.toast(it.toString())
                            }
                    )
        }
        else {
            view?.toast("Fields cannot be empty")
            return false
        }
        return true
    }
}