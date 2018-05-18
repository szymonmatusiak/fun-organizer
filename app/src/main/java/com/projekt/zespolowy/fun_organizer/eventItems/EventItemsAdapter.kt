package com.projekt.zespolowy.fun_organizer.eventItems

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.ViewGroup
import com.projekt.zespolowy.fun_organizer.eventInfo.EventInfo

class EventItemsAdapter(private val userModelList: EventInfo, private val itemsGropusList: MutableList<ArrayList<SingleItemModel>>, private val eventItemsListener: EventItemsListener)
    : RecyclerView.Adapter<EventItemsHolder>(){

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): EventItemsHolder = EventItemsHolder.createViewHolder(viewGroup)


    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(eventViewHolder: EventItemsHolder, position: Int) {
        Log.v("2 Size: ", itemsGropusList.size.toString())
        //eventViewHolder.setData(userModelList.needs.get(position), itemsGropusList.get(0), eventItemsListener)
        eventViewHolder.setData(userModelList.needs.get(position), eventItemsListener)
    }

    override fun getItemCount() = userModelList.needs.size
}