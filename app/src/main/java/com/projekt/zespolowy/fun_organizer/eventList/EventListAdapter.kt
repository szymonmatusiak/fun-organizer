package com.projekt.zespolowy.fun_organizer.eventList

import android.support.design.widget.Snackbar
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.projekt.zespolowy.fun_organizer.R

/**
 *  Adapter class for event list recycle
 */

class EventListAdapter(private val eventList: List<Event>) :
        RecyclerView.Adapter<EventListAdapter.ViewHolder>() {

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val eventName: TextView
        val eventDate: TextView
        val eventPlace: TextView

        init {
            v.setOnClickListener { view ->
                Snackbar.make(view, "Cicked on event id: $adapterPosition", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()}
            eventName = v.findViewById(R.id.event_name_textView)
            eventDate = v.findViewById(R.id.event_date_textView)
            eventPlace = v.findViewById(R.id.event_place_textView)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view.
        val v = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.event_list_element, viewGroup, false)

        return ViewHolder(v)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        //Log.d(TAG, "Element $position set.")

        viewHolder.eventName.text = eventList.get(position).name
        viewHolder.eventDate.text = eventList.get(position).date
        viewHolder.eventPlace.text = eventList.get(position).placeName
    }

    override fun getItemCount() = eventList.size

    /*companion object {
        private val TAG = "EventListAdapter"
    }*/ //Potrzebne tylko do wypisywania LOGów
}