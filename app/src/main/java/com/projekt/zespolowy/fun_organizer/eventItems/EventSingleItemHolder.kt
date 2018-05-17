package com.projekt.zespolowy.fun_organizer.eventItems

import android.support.design.widget.Snackbar
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.projekt.zespolowy.fun_organizer.R

class EventSingleItemHolder(v: View) : RecyclerView.ViewHolder(v)  {
    private val itemName: TextView
    private val itemDescription: TextView
    private val value: TextView

    companion object {
        fun createViewHolder(viewGroup: ViewGroup): EventSingleItemHolder {
            val v = LayoutInflater.from(viewGroup.context)
                    .inflate(R.layout.event_gropus_single_item, viewGroup, false)

            return EventSingleItemHolder(v)
        }
    }

    init {
        v.setOnClickListener { view ->
            Snackbar.make(view, "Cicked on event id: $adapterPosition", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
        itemName = v.findViewById(R.id.event_items_item_textView)
        itemDescription = v.findViewById(R.id.event_items_item_comment_textView)
        value = v.findViewById(R.id.event_items_item_value_textView)
    }

    fun setData(item: SingleItemModel) {
        itemName.text = item.name
        itemDescription.text = item.description
        value.text = item.value.toString()
    }

}