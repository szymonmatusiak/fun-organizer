package com.projekt.zespolowy.fun_organizer.NewEvent

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

class EventNeedsAdapter(private val myDataset: List<EventNeedsModel>):
        RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder = ViewHolder.createViewHolder(viewGroup)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(myDataset[position])
    }

    override fun getItemCount() = myDataset.size
}