package com.projekt.zespolowy.fun_organizer.eventItems

import android.support.design.widget.Snackbar
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.projekt.zespolowy.fun_organizer.R
import com.projekt.zespolowy.fun_organizer.eventInfo.Need

class EventItemsHolder(v: View) : RecyclerView.ViewHolder(v) {
    private val itemName: TextView
    private val itemDescription: TextView

    companion object {
        fun createViewHolder(viewGroup: ViewGroup): EventItemsHolder {
            val v = LayoutInflater.from(viewGroup.context)
                    .inflate(R.layout.event_items_gropus, viewGroup, false)

            return EventItemsHolder(v)
        }
    }

    init {
        v.setOnClickListener { view ->
            Snackbar.make(view, "Cicked on event id: $adapterPosition", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
        itemName = v.findViewById(R.id.event_items_group_name)
        itemDescription = v.findViewById(R.id.event_items_group_description)
    }

    fun setData(need: Need, eventItemsListener: EventItemsListener) {
        itemName.text = need.name
        itemDescription.text = need.description

        itemName.setOnClickListener{eventItemsListener.onEventClicked(need)}
    }

}