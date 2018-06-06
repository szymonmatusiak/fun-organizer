package com.projekt.zespolowy.fun_organizer.eventList

import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.projekt.zespolowy.fun_organizer.R
import kotterknife.bindView
import java.text.SimpleDateFormat

class EventViewHolder(v: View) : RecyclerView.ViewHolder(v) {

    private val eventName: TextView by bindView(R.id.event_name_textView)
    private val eventDate: TextView by bindView(R.id.event_date_textView)
    private val eventPlace: TextView by bindView(R.id.event_place_textView)
    private val viewHolder: ConstraintLayout by bindView(R.id.layout_holder)

    companion object {
        fun createViewHolder(viewGroup: ViewGroup): EventViewHolder {
            val v = LayoutInflater.from(viewGroup.context)
                    .inflate(R.layout.event_list_element, viewGroup, false)

            return EventViewHolder(v)
        }
    }

    fun setData(event: EventModel2, eventListener: EventListener) {
        eventName.text = event.name
        // If jest fixem na starą datę w bazie, nowa powinna działać bez IFa
        if (event.date.length == 16 && event.date[9] == ' ') {
            val dateFormat = SimpleDateFormat("MM-dd-yyyy HH:mm")
            val text = dateFormat.format(event.date) // tutaj data czytana jest jako właściwa data, jednak ze względu na pole zamieniana jest spowrotem na String
            eventDate.text = text
        } else {
            eventDate.text = event.date
        }
        if (event.place.contains("°")) {
            eventPlace.text = event.address
        }
        else{
            eventPlace.text = event.place
        }
        viewHolder.setOnClickListener { eventListener.onEventClicked(event) }
    }

}