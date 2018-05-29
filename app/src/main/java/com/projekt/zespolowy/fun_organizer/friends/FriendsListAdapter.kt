package com.projekt.zespolowy.fun_organizer.friends

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.projekt.zespolowy.fun_organizer.register.UserModel

class FriendsListAdapter(private val userModelList: List<UserModel>) :
        RecyclerView.Adapter<UserViewHolder>() {


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): UserViewHolder = UserViewHolder.createViewHolder(viewGroup)


    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(eventViewHolder: UserViewHolder, position: Int) {
        eventViewHolder.setData(userModelList[position])
    }

    override fun getItemCount() = userModelList.size
}