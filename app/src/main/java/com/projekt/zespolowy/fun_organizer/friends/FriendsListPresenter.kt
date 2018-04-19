package com.projekt.zespolowy.fun_organizer.friends

import com.projekt.zespolowy.fun_organizer.base.BasePresenter
import com.projekt.zespolowy.fun_organizer.utils.SchedulersProvider

class FriendsListPresenter(
        private val friendsListUseCase: FriendsListUseCase,
        private val schedulersProvider: SchedulersProvider) : BasePresenter<FriendsListView>() {

    fun onStart(friendsListView: FriendsListView) {
        attachView(friendsListView)
    }

    fun onStop() {
        detachView(false)
    }

    fun getFriendsList() {
        friendsListUseCase
                .getFriendsList()
                .subscribeOn(schedulersProvider.backgroundThread())
                .observeOn(schedulersProvider.mainThread())
                .subscribe(
                        {
                            view?.setFriendsList(it)
                        },
                        {
                            println(it.toString())
                        }
                )
    }

    fun startNewActivity() {
        view?.startNewEventActivity()

    }

}