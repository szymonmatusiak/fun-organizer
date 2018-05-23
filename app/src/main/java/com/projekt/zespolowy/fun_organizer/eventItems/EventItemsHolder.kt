package com.projekt.zespolowy.fun_organizer.eventItems

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.projekt.zespolowy.fun_organizer.R

class EventItemsHolder(v: View) : RecyclerView.ViewHolder(v) {
    private val itemName: TextView
    private val itemDescription: TextView
    private val itemPrice: TextView
    private val itemBy: TextView

    companion object {
        fun createViewHolder(viewGroup: ViewGroup): EventItemsHolder {
            val v = LayoutInflater.from(viewGroup.context)
                    .inflate(R.layout.event_gropus_single_item, viewGroup, false)
            return EventItemsHolder(v)
        }
    }

    init {
        /*v.setOnClickListener { view ->
            Snackbar.make(view, "Cicked on event id: $adapterPosition", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }*/

        itemName = v.findViewById(R.id.event_items_item_textView)
        itemDescription = v.findViewById(R.id.event_items_item_comment_textView)
        itemPrice = v.findViewById(R.id.event_items_item_value_textView)
        itemBy = v.findViewById(R.id.event_items_item_user_textView)
    }

    //fun setData(need: Need, items: List<SingleItemModel> , eventItemsListener: EventItemsListener) {
    fun setData(item: SingleItemModel, eventItemsListener: EventItemsListener) {

        itemName.text = item.name
        itemDescription.text = item.description
        itemPrice.text = item.value.toString()

        var s = "By "
        if (!item.declared.name.equals("") )
            s += item.declared.name + " "
        if (!item.declared.surname.equals("") )
            s += item.declared.surname
        if (!item.declared.name.equals("") && !item.declared.surname.equals(""))
            s += " (" + item.declared.email + ")"
        else
            s += item.declared.email

        itemBy.text = s

    }

}