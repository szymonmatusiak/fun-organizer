package com.projekt.zespolowy.fun_organizer.friends

import com.projekt.zespolowy.fun_organizer.utils.ApiProvider

class FriendsListUseCase(private val apiProvider: ApiProvider) {
    fun getFriendsList() = apiProvider.getFriendsList()
}