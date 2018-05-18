package com.projekt.zespolowy.fun_organizer.friendsListEvent

import com.projekt.zespolowy.fun_organizer.register.UserModel

interface UserClickListener {
    fun addToList(user: UserModel)
    fun removeFromList(user: UserModel)
}