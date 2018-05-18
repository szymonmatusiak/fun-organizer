package com.projekt.zespolowy.fun_organizer.eventItems

import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.EditText
import androidx.core.widget.toast
import com.projekt.zespolowy.fun_organizer.R
import com.projekt.zespolowy.fun_organizer.eventInfo.EventInfo
import com.projekt.zespolowy.fun_organizer.eventInfo.Need
import com.projekt.zespolowy.fun_organizer.utils.ApiProvider
import com.projekt.zespolowy.fun_organizer.utils.SchedulersProvider
import kotterknife.bindView

class EventItemsActivity : AppCompatActivity(), EventItemsView, EventItemsListener {

    private lateinit var eventItemsPresenter: EventItemsPresenter
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private val recyclerView: RecyclerView by bindView(R.id.event_items_recycle_view)

    private val groupsItemsList: MutableList<ArrayList<SingleItemModel>> = mutableListOf<ArrayList<SingleItemModel>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_items)
        eventItemsPresenter = EventItemsPresenter(EventItemsUseCase(ApiProvider.instance), SchedulersProvider())
    }

    override fun onStart() {
        super.onStart()
        eventItemsPresenter.onStart(this)

        var eventID: String = intent.getStringExtra("eventID")
        eventItemsPresenter.getSingleEvent(Integer.parseInt(eventID))
    }

    override fun setItems(it: EventInfo?) {
        if (it != null) {
            if (it.needs?.size > 0) {
                for (item in it!!.needs) {
                    eventItemsPresenter.getAllCategoryItems(item.id)
                }
            }
        }

        viewManager = LinearLayoutManager(this)
        viewAdapter = EventItemsAdapter(it!!, groupsItemsList, this)
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    override fun setItemsInCategory(itemsList: ArrayList<SingleItemModel>) {
        toast("org:" + itemsList.toString())
        groupsItemsList.add(itemsList)
        toast("cpy: " + groupsItemsList.get(groupsItemsList.lastIndex))
        Log.v("SizeNow", groupsItemsList.size.toString())
    }

    override fun onEventClicked(item: Need) {
        var itemName: String
        val builder = AlertDialog.Builder(this)
        val viewInflated = LayoutInflater.from(this).inflate(R.layout.dialog_add_item, findViewById(android.R.id.content) as ViewGroup, false)
        val inputItem = viewInflated.findViewById(R.id.input_item) as EditText
        val inputDescription = viewInflated.findViewById(R.id.input_description) as EditText
        builder.setTitle("Add item")
        builder.setView(viewInflated)

        builder.setPositiveButton("Add", DialogInterface.OnClickListener { dialog, which ->
            itemName = inputItem.text.toString()
            /*if (itemName != ""){
                this.itemsList.add(EventNeedsModel(itemName,inputDescription.text.toString()))
                viewAdapter.notifyItemInserted(itemsList.size - 1)
            }*/
        })
        builder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })
        builder.show()
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
    }

    override fun onStop() {
        super.onStop()
        eventItemsPresenter.onStop()
    }
}
