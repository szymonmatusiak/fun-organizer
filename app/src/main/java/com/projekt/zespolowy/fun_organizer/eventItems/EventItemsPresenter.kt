package com.projekt.zespolowy.fun_organizer.eventItems

import com.projekt.zespolowy.fun_organizer.base.BasePresenter
import com.projekt.zespolowy.fun_organizer.eventInfo.NeedNoID
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
                            view?.setItems(it)
                        },
                        {
                            println(it.toString())
                        }
                )
    }

    fun postItemToCategory(catID: Int, item: SingleItemSmallModel){
        eventItemsUseCase
                .postItemToCategory(catID, item)
                .subscribeOn(schedulersProvider.backgroundThread())
                .observeOn(schedulersProvider.mainThread())
                .subscribe(
                        {
                            getAllCategoryItems(catID)
                            view?.notifyOnUpdate()
                        },
                        {
                            println(it.toString())
                            view?.myToast("Problem with adding item :(")
                        }
                )
    }

    fun deleteItemInCategory(itemID: Int, catID: Int){
        eventItemsUseCase
                .deleteItemInCategory(itemID)
                .subscribeOn(schedulersProvider.backgroundThread())
                .observeOn(schedulersProvider.mainThread())
                .subscribe(
                        {
                            getAllCategoryItems(catID)
                            view?.notifyOnUpdate()
                        },
                        {
                            view?.myToast("Problem with deleting item :(")
                        }
                )
    }

    fun editItemInCategory(itemID: Int, item: SingleItemSmallModel, catID: Int) {
        eventItemsUseCase
                .editItemInCategory(itemID, item)
                .subscribeOn(schedulersProvider.backgroundThread())
                .observeOn(schedulersProvider.mainThread())
                .subscribe(
                        {
                            getAllCategoryItems(catID)
                            view?.notifyOnUpdate()
                        },
                        {
                            view?.myToast("Problem with editing item :(")
                        }
                )
    }

    fun confirmCategory(groupID: Int, group: NeedNoID){
        eventItemsUseCase
                .confirmCategory(groupID, group)
                .subscribeOn(schedulersProvider.backgroundThread())
                .observeOn(schedulersProvider.mainThread())
                .subscribe(
                        {
                            getAllCategoryItems(groupID)
                            view?.notifyOnUpdate()
                        },
                        {
                            view?.myToast("Problem with editing item :(")
                        }
                )
    }
}