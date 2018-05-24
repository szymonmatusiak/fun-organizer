package com.projekt.zespolowy.fun_organizer.invitations

import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.projekt.zespolowy.fun_organizer.R
import kotterknife.bindView

class InvitationViewHolder(v: View) : RecyclerView.ViewHolder(v) {

    private val eventName: TextView by bindView(R.id.event_name)
    private val eventHost: TextView by bindView(R.id.event_host)
    private val eventDate: TextView by bindView(R.id.event_date)
    private val accept: Button by bindView(R.id.accept)
    private val later: Button by bindView(R.id.later)
    private val decline: Button by bindView(R.id.decline)
    private val viewHolder: ConstraintLayout by bindView(R.id.layout_holder)

    companion object {
        fun createViewHolder(viewGroup: ViewGroup): InvitationViewHolder {
            val v = LayoutInflater.from(viewGroup.context)
                    .inflate(R.layout.invitation, viewGroup, false)

            return InvitationViewHolder(v)
        }
    }

    fun setData(invitation: Invitation, invitationsListener: InvitationsListener) {
        eventName.text = invitation.event.name
        eventHost.text = invitation.event.host.name + invitation.event.host.surname
        eventDate.text = invitation.event.date
        accept.setOnClickListener { invitationsListener.onEventClicked(invitation, accept) }
        later.setOnClickListener { invitationsListener.onEventClicked(invitation, later) }
        decline.setOnClickListener { invitationsListener.onEventClicked(invitation, decline) }
    }

}