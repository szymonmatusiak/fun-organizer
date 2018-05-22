package com.projekt.zespolowy.fun_organizer.eventInfo

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.ViewGroup
import com.projekt.zespolowy.fun_organizer.eventInfo.EventInfo

class EventInfoItemsGroupsAdapter(private val itemsGropusList: MutableList<Need>, private val eventItemsListener: EventInfoItemsGoupsListener)
    : RecyclerView.Adapter<EventInfoItemsGroupsHolder>(){

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): EventInfoItemsGroupsHolder = EventInfoItemsGroupsHolder.createViewHolder(viewGroup)


    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(eventViewHolder: EventInfoItemsGroupsHolder, position: Int) {
        //Log.v("2 Size: ", itemsGropusList.size.toString())
        //eventViewHolder.setData(userModelList.needs.get(position), itemsGropusList.get(position), eventItemsListener)
        eventViewHolder.setData(itemsGropusList[position], eventItemsListener)
    }

    override fun getItemCount() = itemsGropusList.size
}