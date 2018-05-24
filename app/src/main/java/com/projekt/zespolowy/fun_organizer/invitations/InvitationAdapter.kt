package com.projekt.zespolowy.fun_organizer.invitations

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup


class InvitationAdapter(private val eventModelList: List<Invitation>, private val invitationsListener: InvitationsListener) :
        RecyclerView.Adapter<InvitationViewHolder>() {

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): InvitationViewHolder = InvitationViewHolder.createViewHolder(viewGroup)


    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(invitationViewHolder: InvitationViewHolder, position: Int) {
        invitationViewHolder.setData(eventModelList[position], invitationsListener)
    }


    override fun getItemCount() = eventModelList.size
}