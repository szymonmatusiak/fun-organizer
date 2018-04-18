package com.projekt.zespolowy.fun_organizer.friends


import android.support.design.widget.Snackbar
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.projekt.zespolowy.fun_organizer.R
import com.projekt.zespolowy.fun_organizer.register.UserModel

class UserViewHolder(v: View) : RecyclerView.ViewHolder(v) {
    private val eventName: TextView
    private val eventDate: TextView
    private val eventPlace: TextView

    companion object {
        fun createViewHolder(viewGroup: ViewGroup): UserViewHolder {
            val v = LayoutInflater.from(viewGroup.context)
                    .inflate(R.layout.event_list_element, viewGroup, false)

            return UserViewHolder(v)
        }
    }

    init {
        v.setOnClickListener { view ->
            Snackbar.make(view, "Cicked on event id: $adapterPosition", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
        eventName = v.findViewById(R.id.event_name_textView)
        eventDate = v.findViewById(R.id.event_date_textView)
        eventPlace = v.findViewById(R.id.event_place_textView)
    }

    fun setData(user: UserModel) {
        eventName.text = user.name
        eventDate.text = user.email
        eventPlace.text = user.surname
    }


}