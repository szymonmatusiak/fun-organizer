package com.projekt.zespolowy.fun_organizer.friendsListEvent

import android.graphics.Color
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.projekt.zespolowy.fun_organizer.R
import com.projekt.zespolowy.fun_organizer.register.UserModel
import kotterknife.bindView

class UserAddToEventViewHolder(v: View) : RecyclerView.ViewHolder(v) {

    private val name: TextView by bindView(R.id.username_recycler)
    private val surname: TextView by bindView(R.id.surname_recycler)
    private val mail: TextView by bindView(R.id.mail_recycler)
    private var isClicked: Boolean = false
    private val viewHolder: ConstraintLayout by bindView(R.id.layout_holder)

    companion object {
        fun createViewHolder(viewGroup: ViewGroup): UserAddToEventViewHolder {
            val v = LayoutInflater.from(viewGroup.context)
                    .inflate(R.layout.user_list_element, viewGroup, false)

            return UserAddToEventViewHolder(v)
        }
    }

    fun setData(user: UserModel, userClickListener: UserClickListener) {
        name.text = user.name
        surname.text = user.surname
        mail.text = user.email
        viewHolder.setOnClickListener {
            isClicked = if (!isClicked) {
                this.itemView.setBackgroundColor(Color.parseColor("#35589e"))
                userClickListener.addToList(user)
                !isClicked
            } else {
                this.itemView.setBackgroundColor(Color.parseColor("#f9f9f9"))
                userClickListener.removeFromList(user)
                !isClicked
            }
        }

    }

}