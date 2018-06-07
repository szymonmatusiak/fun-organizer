package com.projekt.zespolowy.fun_organizer.friends


import android.support.design.widget.Snackbar
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.projekt.zespolowy.fun_organizer.R
import com.projekt.zespolowy.fun_organizer.register.UserModel

class UserViewHolder(v: View) : RecyclerView.ViewHolder(v) {
    private val userName: TextView
    private val userSurname: TextView
    private val userMail: TextView

    companion object {
        fun createViewHolder(viewGroup: ViewGroup): UserViewHolder {
            val v = LayoutInflater.from(viewGroup.context)
                    .inflate(R.layout.user_list_element, viewGroup, false)

            return UserViewHolder(v)
        }
    }

    init {
        /*v.setOnClickListener { view ->
            Snackbar.make(view, "Cicked on event id: $adapterPosition", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }*/
        userName = v.findViewById(R.id.username_recycler)
        userSurname = v.findViewById(R.id.surname_recycler)
        userMail = v.findViewById(R.id.mail_recycler)
    }

    fun setData(user: UserModel) {
        userName.text = user.name + " " + user.surname
        userSurname.text = user.email
        userMail.text = user.phoneNumber
    }


}