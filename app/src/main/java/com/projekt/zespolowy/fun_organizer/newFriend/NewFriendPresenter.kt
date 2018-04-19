package com.projekt.zespolowy.fun_organizer.newFriend

import com.projekt.zespolowy.fun_organizer.base.BasePresenter
import com.projekt.zespolowy.fun_organizer.utils.SchedulersProvider

class NewFriendPresenter(
        private val friendUseCase: NewFriendUseCase,
        private val schedulersProvider: SchedulersProvider
) : BasePresenter<NewFriendView>() {


    fun onStart(newFriendView: NewFriendView) {
        attachView(newFriendView)
    }

    fun onStop() {
        detachView(false)
    }


}