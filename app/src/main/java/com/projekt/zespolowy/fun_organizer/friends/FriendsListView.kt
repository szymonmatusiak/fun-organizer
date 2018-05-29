package com.projekt.zespolowy.fun_organizer.friends

import com.projekt.zespolowy.fun_organizer.base.IBaseView
import com.projekt.zespolowy.fun_organizer.register.UserModel

interface FriendsListView : IBaseView {
    fun setFriendsList(it: List<UserModel>?)
    fun startNewEventActivity()
}