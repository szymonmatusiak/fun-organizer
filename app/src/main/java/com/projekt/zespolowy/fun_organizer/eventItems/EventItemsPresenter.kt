package com.projekt.zespolowy.fun_organizer.eventItems

import com.projekt.zespolowy.fun_organizer.base.BasePresenter
import com.projekt.zespolowy.fun_organizer.utils.SchedulersProvider

class EventItemsPresenter(
        private val eventItemsUseCase: EventItemsUseCase,
        private val schedulersProvider: SchedulersProvider) : BasePresenter<EventItemsView>() {

    fun onStart(eventItemsView: EventItemsView) {
        attachView(eventItemsView)
    }

    fun onStop() {
        detachView(false)
    }

    fun getAllCategoryItems(groupID: Int) {
        eventItemsUseCase
                .getAllCategoryItems(groupID)
                .subscribeOn(schedulersProvider.backgroundThread())
                .observeOn(schedulersProvider.mainThread())
                .subscribe(
                        {
                            //view?.setItems(it)
                            view?.setItems(it)
                        },
                        {
                            println(it.toString())
                        }
                )
    }

}