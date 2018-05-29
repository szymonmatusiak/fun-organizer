package com.projekt.zespolowy.fun_organizer.friendsListEvent

import android.util.Log
import com.projekt.zespolowy.fun_organizer.base.BasePresenter
import com.projekt.zespolowy.fun_organizer.friends.FriendsListUseCase
import com.projekt.zespolowy.fun_organizer.register.UserModel
import com.projekt.zespolowy.fun_organizer.utils.SchedulersProvider

class FriendsEventPresenter(
        private val friendsListUseCase: FriendsListUseCase,
        private val schedulersProvider: SchedulersProvider) : BasePresenter<AddFriendsToEventView>() {
    var list: MutableList<String> = mutableListOf<String>()
    private lateinit var inviteData: InviteData
    fun onStart(addFriendsToEventView: AddFriendsToEventView) {
        attachView(addFriendsToEventView)
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

    fun addToList(user: UserModel) {
        inviteData.emails.add(user.email)
        list.add(user.email)
        //view?.toast(list.toString())

        Log.v("test", list.toString())
    }

    fun toast() {
        //view?.toast(list.toString())

    }

    fun removeFromList(user: UserModel) {
        list.remove(user.email)
    }

    fun sendList() {
        friendsListUseCase
                .sendInvitationToEvent(inviteData)
                .subscribeOn(schedulersProvider.backgroundThread())
                .observeOn(schedulersProvider.mainThread())
                .subscribe(
                        {
                            view?.closeActivity()
                        },
                        {
                        }
                )

    }

    fun saveEventID(eventID: String) {
        inviteData = InviteData(eventID, list)

    }
}