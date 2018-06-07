package com.projekt.zespolowy.fun_organizer.eventChat

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.projekt.zespolowy.fun_organizer.R

class ChatHolder(v: View) : RecyclerView.ViewHolder(v) {
    private val chatUser: TextView
    private val chatText: TextView
    private val chatDate: TextView


    companion object {
        fun createViewHolder(viewGroup: ViewGroup): ChatHolder {
            val v = LayoutInflater.from(viewGroup.context)
                    .inflate(R.layout.event_chat_element, viewGroup, false)
            // Wyżej ogarnąć layout :)
            return ChatHolder(v)
        }
    }

    init {
        v.setOnClickListener { }/*view ->
            Snackbar.make(view, "Cicked on event id: $adapterPosition", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }*/

        chatUser = v.findViewById(R.id.chat_author)
        chatText = v.findViewById(R.id.chat_message)
        chatDate = v.findViewById(R.id.chat_date)
    }

    fun setData(eventChatModel: EventChatModel) {
        chatUser.text = eventChatModel.poster.name + " " + eventChatModel.poster.surname
        chatText.text = eventChatModel.text
        chatDate.text = eventChatModel.date
    }
}