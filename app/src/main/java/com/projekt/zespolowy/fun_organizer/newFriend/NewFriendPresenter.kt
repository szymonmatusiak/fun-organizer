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

    fun searchWithPhoneNumber(phone: String) {
        friendUseCase
                .searchWithPhoneNumber(phone)
                .subscribeOn(schedulersProvider.backgroundThread())
                .observeOn(schedulersProvider.mainThread())
                .subscribe(
                        {
                            val raw = it.raw()
                            if (raw.isSuccessful)
                                view?.exitActivity()
                            else
                                view?.toast(raw.code().toString())

                        },
                        {

                        }
                )
    }

    fun searchWithMail(mail: String) {
        friendUseCase
                .searchWithMail(mail)
                .subscribeOn(schedulersProvider.backgroundThread())
                .observeOn(schedulersProvider.mainThread())
                .subscribe(
                        {
                            val raw = it.raw()
                            if (raw.code() == 200)
                                view?.exitActivity()
                            else
                                view?.toast(raw.code().toString())

                        },
                        {

                        }
                )
    }

    fun searchWithContactList(contactList: List<String>) {
        friendUseCase
                .searchWithContactList(contactList)
                .subscribeOn(schedulersProvider.backgroundThread())
                .observeOn(schedulersProvider.mainThread())
                .subscribe(
                        {
                            val raw = it.raw()
                            if (raw.isSuccessful)
                                view?.exitActivity()
                            else
                                view?.toast(raw.code().toString())

                        },
                        {

                        }
                )
    }


}