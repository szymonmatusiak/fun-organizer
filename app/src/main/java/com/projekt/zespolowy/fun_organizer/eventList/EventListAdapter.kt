package com.projekt.zespolowy.fun_organizer.eventList

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup


class EventListAdapter(private val eventModelList: List<EventModel2>, private val eventListener: EventListener) :
        RecyclerView.Adapter<EventViewHolder>() {

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): EventViewHolder = EventViewHolder.createViewHolder(viewGroup)


    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(eventViewHolder: EventViewHolder, position: Int) {
        eventViewHolder.setData(eventModelList[position], eventListener)
    }


    override fun getItemCount() = eventModelList.size
}