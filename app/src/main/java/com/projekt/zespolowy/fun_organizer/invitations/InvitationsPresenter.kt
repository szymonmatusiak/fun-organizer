package com.projekt.zespolowy.fun_organizer.invitations

import com.projekt.zespolowy.fun_organizer.base.BasePresenter
import com.projekt.zespolowy.fun_organizer.utils.SchedulersProvider

class InvitationsPresenter(
        private val invitationUseCase: InvitationsUseCase,
        private val schedulersProvider: SchedulersProvider) : BasePresenter<InvitationListView>() {


    fun onStart(invitationsFragment: InvitationsFragment) {
        attachView(invitationsFragment)
    }

    fun onStop(boolean: Boolean) {
        detachView(boolean)
    }

    fun getUserInvitations() {
        invitationUseCase
                .getUserInvitations()
                .subscribeOn(schedulersProvider.backgroundThread())
                .observeOn(schedulersProvider.mainThread())
                .subscribe(
                        {
                            view?.setEvents(it)
                            view?.notyfyAdapter()
                        },
                        {
                        }
                )
    }

    fun sentInvitationDesition(id: Int, i: Int) {
        invitationUseCase
                .sentInvitationDesition(id, i)
                .subscribeOn(schedulersProvider.backgroundThread())
                .observeOn(schedulersProvider.mainThread())
                .subscribe(
                        {
                            getUserInvitations()
                        },
                        {
                            getUserInvitations()
                        }
                )
    }

}