package com.projekt.zespolowy.fun_organizer.eventGuests

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.projekt.zespolowy.fun_organizer.R
import com.projekt.zespolowy.fun_organizer.register.UserModelNoPassword
import kotterknife.bindView

class GuestViewHolder(v: View) : RecyclerView.ViewHolder(v) {
    private val name: TextView by bindView(R.id.username_recycler)
    private val surname: TextView by bindView(R.id.surname_recycler)
    private val mail: TextView by bindView(R.id.mail_recycler)

    companion object {
        fun createViewHolder(viewGroup: ViewGroup): GuestViewHolder {
            val v = LayoutInflater.from(viewGroup.context)
                    .inflate(R.layout.user_list_element, viewGroup, false)

            return GuestViewHolder(v)
        }
    }

    fun setData(user: UserModelNoPassword) {
        name.text = user.name
        surname.text = user.surname
        mail.text = user.email
    }
}