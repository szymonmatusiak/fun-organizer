package com.projekt.zespolowy.fun_organizer.NewEvent

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
        if (event.placeName.trim().length <= 0 || event.name.trim().length <= 0)
            return false
        else
            return true
    }

    fun postEventToDatabase(event: EventModel): Boolean {
        if(checkCorrectness(event) == true) {
            eventUseCase
                    .postEventToDatabase(event)
                    .subscribeOn(schedulersProvider.backgroundThread())
                    .observeOn(schedulersProvider.mainThread())
                    .subscribe(
                            {
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