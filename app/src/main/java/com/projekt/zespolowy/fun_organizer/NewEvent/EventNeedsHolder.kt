package com.projekt.zespolowy.fun_organizer.NewEvent

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.projekt.zespolowy.fun_organizer.R
import kotterknife.bindView

class ViewHolder(v: View) : RecyclerView.ViewHolder(v){

    private val itemName: TextView by bindView(R.id.new_event_need_textView)
    companion object {
        fun createViewHolder(viewGroup: ViewGroup): ViewHolder {
           val v = LayoutInflater.from(viewGroup.context)
                  .inflate(R.layout.new_event_item_list_element, viewGroup, false)
           return ViewHolder(v)
      }
    }

    fun setData(needs: EventNeedsModel) {
        itemName.text = needs.name
    }
}