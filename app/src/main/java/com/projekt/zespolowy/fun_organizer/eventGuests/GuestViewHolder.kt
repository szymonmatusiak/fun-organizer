package com.projekt.zespolowy.fun_organizer.eventGuests

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import com.projekt.zespolowy.fun_organizer.R
import com.projekt.zespolowy.fun_organizer.register.UserModelNoPassword
import kotterknife.bindView

class GuestViewHolder(v: View) : RecyclerView.ViewHolder(v) {
    private val name: TextView by bindView(R.id.username_recycler)
    private val surname: TextView by bindView(R.id.surname_recycler)
    private val phone: TextView by bindView(R.id.mail_recycler)
    //private val phoneLabel: TextView by bindView(R.id.phoneLabel)

    companion object {
        fun createViewHolder(viewGroup: ViewGroup): GuestViewHolder {
            val v = LayoutInflater.from(viewGroup.context)
                    .inflate(R.layout.user_list_element, viewGroup, false)
            Log.v("QQQQQQQQQQQ", "testestestes")
            return GuestViewHolder(v)
        }
    }

    fun setData(user: UserModelNoPassword) {
        name.text = user.name + " " + user.surname
        surname.text = user.email
        phone.text = user.phoneNumber
        Log.v("QQQQQQQQQQQ", user.toString())
    }
}