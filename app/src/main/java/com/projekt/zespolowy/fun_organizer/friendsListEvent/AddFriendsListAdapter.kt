package com.projekt.zespolowy.fun_organizer.friendsListEvent

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.projekt.zespolowy.fun_organizer.register.UserModel

class AddFriendsListAdapter(private val userModelList: List<UserModel>,
                            private val userClickListener: UserClickListener) : RecyclerView.Adapter<UserAddToEventViewHolder>() {

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): UserAddToEventViewHolder = UserAddToEventViewHolder.createViewHolder(viewGroup)


    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(userViewHolder: UserAddToEventViewHolder, position: Int) {
        userViewHolder.setData(userModelList[position], userClickListener)
    }


    override fun getItemCount() = userModelList.size
}