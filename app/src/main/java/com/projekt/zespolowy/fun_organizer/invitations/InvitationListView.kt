package com.projekt.zespolowy.fun_organizer.invitations

import com.projekt.zespolowy.fun_organizer.base.IBaseView

interface InvitationListView : IBaseView {
    fun toast(toString: String)
    fun setEvents(it: List<Invitation>?)
    fun notyfyAdapter()
}
