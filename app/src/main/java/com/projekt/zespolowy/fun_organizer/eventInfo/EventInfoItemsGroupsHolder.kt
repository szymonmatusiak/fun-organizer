package com.projekt.zespolowy.fun_organizer.eventInfo

import android.graphics.Color
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.isVisible
import com.projekt.zespolowy.fun_organizer.R
import kotterknife.bindView

class EventInfoItemsGroupsHolder(v: View) : RecyclerView.ViewHolder(v) {
    private val itemName: TextView
    private val itemDescription: TextView
    //private val numberOfItems: TextView
    private val enoughIcon: ImageView
    private val notEnoughIcon: ImageView
    private val layout: LinearLayout
    private val viewHolder: ConstraintLayout by bindView(R.id.event_info_items_group_holder)

    companion object {
        fun createViewHolder(viewGroup: ViewGroup): EventInfoItemsGroupsHolder {
            val v = LayoutInflater.from(viewGroup.context)
                    .inflate(R.layout.event_items_gropus, viewGroup, false)
            return EventInfoItemsGroupsHolder(v)
        }
    }

    init {
        v.setOnClickListener { }/*view ->
            Snackbar.make(view, "Cicked on event id: $adapterPosition", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }*/

        itemName = v.findViewById(R.id.event_items_group_name)
        itemDescription = v.findViewById(R.id.event_items_group_description)
        enoughIcon = v.findViewById(R.id.event_info_enough_items)
        notEnoughIcon = v.findViewById(R.id.event_info_not_enough_items)
        layout = v.findViewById((R.id.single_group_layout))

        viewHolder.setOnClickListener({})
    }

    fun setData(need: Need , iAmHost:Boolean, eventItemsListener: EventInfoItemsGoupsListener) {

        itemName.text = need.name
        itemDescription.text = need.description

        enoughIcon.isEnabled = need.enough
        enoughIcon.isVisible = need.enough
        notEnoughIcon.isEnabled = !need.enough
        notEnoughIcon.isVisible = !need.enough

        //Przetestować czy działa jak jest zaakceptowane a jestem hostem
        //if (!need.enough || iAmHost){
        viewHolder.setOnClickListener{eventItemsListener.onEventClicked(need)}
        //}

        if (need.enough){
            layout.setBackgroundColor(Color.parseColor("#817ef67c"))
        }


    }

}