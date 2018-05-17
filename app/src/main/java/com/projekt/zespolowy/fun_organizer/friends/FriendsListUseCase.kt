package com.projekt.zespolowy.fun_organizer.friends

import com.projekt.zespolowy.fun_organizer.friendsListEvent.InviteData
import com.projekt.zespolowy.fun_organizer.utils.ApiProvider

class FriendsListUseCase(private val apiProvider: ApiProvider) {
    fun getFriendsList() = apiProvider.getFriendsList()
    fun sendInvitationToEvent(inviteData: InviteData) = apiProvider.sendInvitationToEvent(inviteData)
}