package com.projekt.zespolowy.fun_organizer.eventItems

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

class EventItemsAdapter(private val itemsGropusList: MutableList<SingleItemModel>, private val iAmHost:Boolean, private val eventItemsListener: EventItemsListener)
    : RecyclerView.Adapter<EventItemsHolder>(){

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): EventItemsHolder = EventItemsHolder.createViewHolder(viewGroup)

    override fun onBindViewHolder(eventViewHolder: EventItemsHolder, position: Int) {
        eventViewHolder.setData(itemsGropusList.get(position), iAmHost,  eventItemsListener)
    }

    override fun getItemCount() = itemsGropusList.size
}