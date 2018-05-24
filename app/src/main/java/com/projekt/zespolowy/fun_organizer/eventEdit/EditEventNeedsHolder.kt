package com.projekt.zespolowy.fun_organizer.eventEdit

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.projekt.zespolowy.fun_organizer.R
import kotterknife.bindView

/**
 * Created by viktor on 24.05.2018.
 */

class EditEventNeedsHolder(v: View) : RecyclerView.ViewHolder(v){

    private val itemName: TextView by bindView(R.id.edit_event_need_textView)
    private val itemComment: TextView by bindView(R.id.edit_event_need_comment_textView)
    private val buttonDeleteItem: ImageView by bindView(R.id.edit_event_delete_item)

    companion object {
        fun createViewHolder(viewGroup: ViewGroup): EditEventNeedsHolder {
            val v = LayoutInflater.from(viewGroup.context)
                    .inflate(R.layout.edit_event_item_list_element, viewGroup, false)
            return EditEventNeedsHolder(v)
        }
    }

    fun setData(needs: EventNeedsModel, itemListener: EditItemListener) {
        itemName.text = needs.name
        itemComment.text = needs.description
        buttonDeleteItem.setOnClickListener{itemListener.onDeleteClicked(needs)}
    }
}