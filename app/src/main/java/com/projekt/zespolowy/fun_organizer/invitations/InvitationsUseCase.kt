package com.projekt.zespolowy.fun_organizer.invitations

import com.projekt.zespolowy.fun_organizer.utils.ApiProvider


class InvitationsUseCase(private val apiProvider: ApiProvider) {
    fun getUserInvitations() = apiProvider.getUserInvitations()
    fun sentInvitationDesition(id: Int, i: Int)= apiProvider.sentInvitationDesition(id,i)

}
