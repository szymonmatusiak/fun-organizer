package com.projekt.zespolowy.fun_organizer.eventGuests

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.ViewGroup
import com.projekt.zespolowy.fun_organizer.register.UserModelNoPassword

class GuestAdapter(private val userModelList: List<UserModelNoPassword>) : RecyclerView.Adapter<GuestViewHolder>() {

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): GuestViewHolder = GuestViewHolder.createViewHolder(viewGroup)


    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(guestViewHolder: GuestViewHolder, position: Int) {
        Log.v("QQQQQQQQQQQ",
                "tesetestest")

        guestViewHolder.setData(userModelList[position])
    }


    override fun getItemCount() = userModelList.size
}