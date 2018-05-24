package com.projekt.zespolowy.fun_organizer.eventItems

import android.preference.PreferenceManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.isVisible
import com.projekt.zespolowy.fun_organizer.MyApplication
import com.projekt.zespolowy.fun_organizer.R

class EventItemsHolder(v: View) : RecyclerView.ViewHolder(v) {
    private val itemName: TextView
    private val itemDescription: TextView
    private val itemPrice: TextView
    private val itemBy: TextView
    private val deleteItem: ImageView
    private val editItem: LinearLayout

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
        deleteItem = v.findViewById(R.id.new_event_delete_item)
        editItem = v.findViewById(R.id.single_event)

        deleteItem.setOnClickListener({})
        editItem.setOnClickListener({})

    }

    fun setData(item: SingleItemModel, iAmHost:Boolean, eventItemsListener: EventItemsListener) {

        itemName.text = item.name
        itemDescription.text = item.description

        if (item.value.toString().length > 2)
            itemPrice.text = item.value.toString().substring(0,item.value.toString().length-2) + "." + item.value.toString().substring(item.value.toString().length-2) + " PLN"
        else if (item.value.toString().length > 1)
            itemPrice.text = item.value.toString().substring(0,item.value.toString().length-2) + "0." + item.value.toString().substring(item.value.toString().length-2) + " PLN"
        else
            itemPrice.text = item.value.toString()

        var owner = "By "
        if (!item.declared.name.equals("") )
            owner += item.declared.name + " "
        if (!item.declared.surname.equals("") )
            owner += item.declared.surname
        if (!item.declared.name.equals("") && !item.declared.surname.equals(""))
            owner += " (" + item.declared.email + ")"
        else
            owner += item.declared.email
        itemBy.text = owner

        var iAmOwner = false
        val defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(MyApplication.appContext)
        var email = defaultSharedPreferences.getString("email", "Missing")
        if (email.equals(item.declared.email))
            iAmOwner = true

        //sprawdzić czy działa jak szymon swoje zroib
        if (iAmHost || iAmOwner){
            editItem.setOnClickListener({eventItemsListener.onEventClicked(item)})
            deleteItem.setOnClickListener({eventItemsListener.onDeleteClicked(item, adapterPosition)})
        }
        else{
            deleteItem.isVisible = false
            deleteItem.isEnabled = false
        }

    }

}