package com.projekt.zespolowy.fun_organizer.eventItems

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

class EventSingleItemAdapter(private val groupItems: Array<SingleItemModel>)
    : RecyclerView.Adapter<EventSingleItemHolder>() {
    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): EventSingleItemHolder = EventSingleItemHolder.createViewHolder(viewGroup)


    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(eventViewHolder: EventSingleItemHolder, position: Int) {
        eventViewHolder.setData(groupItems.get(position))
    }

    override fun getItemCount() = groupItems.size
}