package com.projekt.zespolowy.fun_organizer.eventItems

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.ViewGroup
import com.projekt.zespolowy.fun_organizer.eventInfo.EventInfo

class EventItemsAdapter(private val itemsGropusList: MutableList<SingleItemModel>, private val eventItemsListener: EventItemsListener)
    : RecyclerView.Adapter<EventItemsHolder>(){

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): EventItemsHolder = EventItemsHolder.createViewHolder(viewGroup)

    override fun onBindViewHolder(eventViewHolder: EventItemsHolder, position: Int) {
        eventViewHolder.setData(itemsGropusList.get(position), eventItemsListener)
    }

    override fun getItemCount() = itemsGropusList.size
}