package com.projekt.zespolowy.fun_organizer.friendsListEvent

import com.projekt.zespolowy.fun_organizer.base.IBaseView
import com.projekt.zespolowy.fun_organizer.register.UserModel

interface AddFriendsToEventView : IBaseView {
    fun setFriendsList(it: List<UserModel>?)
    fun toast(toString: String)
    fun closeActivity()
}