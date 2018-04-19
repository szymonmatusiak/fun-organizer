package com.projekt.zespolowy.fun_organizer.eventList

import android.support.design.widget.Snackbar
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.projekt.zespolowy.fun_organizer.R
import java.text.SimpleDateFormat


class EventViewHolder(v: View) : RecyclerView.ViewHolder(v) {

    private val eventName: TextView
    private val eventDate: TextView
    private val eventPlace: TextView

    companion object {
        fun createViewHolder(viewGroup: ViewGroup): EventViewHolder {
            val v = LayoutInflater.from(viewGroup.context)
                    .inflate(R.layout.event_list_element, viewGroup, false)

            return EventViewHolder(v)
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

    fun setData(event: EventModel2) {
        eventName.text = event.name
        // If jest fixem na starą datę w bazie, nowa powinna działać bez IFa
        if (event.date.length == 16 && event.date[9] == ' '){
            val dateFormat = SimpleDateFormat("MM-dd-yyyy HH:mm")
            val text = dateFormat.format(event.date) // tutaj data czytana jest jako właściwa data, jednak ze względu na pole zamieniana jest spowrotem na String
            eventDate.text = text
        }
        else{
            eventDate.text = event.date
        }
        eventPlace.text = event.placeName
    }

}