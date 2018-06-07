package com.projekt.zespolowy.fun_organizer.eventChat

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

class ChatAdapter(private val userModelList: List<EventChatModel>) : RecyclerView.Adapter<ChatHolder>() {

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ChatHolder = ChatHolder.createViewHolder(viewGroup)


    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(guestViewHolder: ChatHolder, position: Int) {
        /*Log.v("QQQQQQQQQQQ",
                "tesetestest")*/

        guestViewHolder.setData(userModelList[position])
    }


    override fun getItemCount() = userModelList.size
}