package com.projekt.zespolowy.fun_organizer.newEvent

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.projekt.zespolowy.fun_organizer.R
import kotterknife.bindView

class EventNeedsHolder(v: View) : RecyclerView.ViewHolder(v){

    private val itemName: TextView by bindView(R.id.new_event_need_textView)
    private val itemComment: TextView by bindView(R.id.new_event_need_comment_textView)
    companion object {
        fun createViewHolder(viewGroup: ViewGroup): EventNeedsHolder {
           val v = LayoutInflater.from(viewGroup.context)
                  .inflate(R.layout.new_event_item_list_element, viewGroup, false)
           return EventNeedsHolder(v)
      }
    }

    fun setData(needs: EventNeedsModel) {
        itemName.text = needs.name
        itemComment.text = needs.description
    }
}