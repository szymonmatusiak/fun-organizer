package com.projekt.zespolowy.fun_organizer.eventEdit

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

/**
 * Created by viktor on 24.05.2018.
 */

class EditEventNeedsAdapter(private val myDataSet: List<EventNeedsModel>, private val editItemListener: EditItemListener):
        RecyclerView.Adapter<EditEventNeedsHolder>() {
        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): EditEventNeedsHolder = EditEventNeedsHolder.createViewHolder(viewGroup)

   override fun onBindViewHolder(holder: EditEventNeedsHolder, position: Int) {
       holder.setData(myDataSet[position], editItemListener)
    }

    override fun getItemCount() = myDataSet.size
}