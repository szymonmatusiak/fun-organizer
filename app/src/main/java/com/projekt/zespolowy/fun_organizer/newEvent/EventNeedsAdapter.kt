package com.projekt.zespolowy.fun_organizer.newEvent

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import java.util.*

class EventNeedsAdapter(private val myDataset: List<EventNeedsModel>, private val itemListener: ItemListener):
        RecyclerView.Adapter<EventNeedsHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): EventNeedsHolder = EventNeedsHolder.createViewHolder(viewGroup)

    override fun onBindViewHolder(holder: EventNeedsHolder, position: Int) {
        holder.setData(myDataset[position], itemListener)
    }

    override fun getItemCount() = myDataset.size
}