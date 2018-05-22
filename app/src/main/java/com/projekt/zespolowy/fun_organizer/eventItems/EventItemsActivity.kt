package com.projekt.zespolowy.fun_organizer.eventItems

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.projekt.zespolowy.fun_organizer.R
import com.projekt.zespolowy.fun_organizer.utils.ApiProvider
import com.projekt.zespolowy.fun_organizer.utils.SchedulersProvider
import kotlinx.android.synthetic.main.activity_event_items.*
import kotterknife.bindView


//TODO - ogarnąć wyświetlanie pojedyńczycg itemów grupy
class EventItemsActivity : AppCompatActivity(), EventItemsView, EventItemsListener {

    private lateinit var eventItemsPresenter: EventItemsPresenter
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private val recyclerView: RecyclerView by bindView(R.id.event_items_recycle_view)

    private val groupsItemsList: MutableList<SingleItemModel> = mutableListOf<SingleItemModel>()

    private var groupName: String = ""
    private var groupDescription: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_items)
        eventItemsPresenter = EventItemsPresenter(EventItemsUseCase(ApiProvider.instance), SchedulersProvider())
    }

    override fun onStart() {
        super.onStart()
        eventItemsPresenter.onStart(this)

        var groupID: String = intent.getStringExtra("groupID")

        eventItemsPresenter.getAllCategoryItems(Integer.parseInt(groupID))

        var groupName: String = intent.getStringExtra("itemName")
        var groupDescription: String = intent.getStringExtra("description")

        event_items_activity_group_name.text = groupName
        event_items_activity_group_description.text = groupDescription

    }

    override fun setItems(it: MutableList<SingleItemModel>?) {

        viewManager = LinearLayoutManager(this)
        viewAdapter = EventItemsAdapter(it!!, this)
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

   /* override fun setItemsInCategory(itemsList: ArrayList<SingleItemModel>) {
        toast("org:" + itemsList.toString())
        //groupsItemsList.add(itemsList)
        toast("cpy: " + groupsItemsList.get(groupsItemsList.lastIndex))
        Log.v("SizeNow", groupsItemsList.size.toString())
    }*/

    override fun onEventClicked(item: SingleItemModel) {
        //toast("heh")
    }

    override fun onStop() {
        super.onStop()
        eventItemsPresenter.onStop()
    }
}
