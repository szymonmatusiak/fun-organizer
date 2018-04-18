package com.projekt.zespolowy.fun_organizer.eventList

import android.app.Fragment
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.projekt.zespolowy.fun_organizer.R
import com.projekt.zespolowy.fun_organizer.navigation.BlankFragment2
import com.projekt.zespolowy.fun_organizer.newEvent.NewEventActivity
import com.projekt.zespolowy.fun_organizer.utils.ApiProvider
import com.projekt.zespolowy.fun_organizer.utils.SchedulersProvider
import kotterknife.bindView

class EventListFragment : Fragment(), EventListView {

    private lateinit var eventListPresenter: EventListPresenter
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private val recyclerView: RecyclerView by bindView(R.id.event_list_recycle_view)
    private val newEventButton: FloatingActionButton by bindView(R.id.new_event_button)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        eventListPresenter = EventListPresenter(EventListUseCase(ApiProvider.instance), SchedulersProvider())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_event_list, container, false)
    }

    override fun onStart() {
        super.onStart()
        eventListPresenter.onStart(this)
        eventListPresenter.getEventList()
        newEventButton.setOnClickListener {
            eventListPresenter.startNewActivity()
        }
    }

    override fun onStop() {
        super.onStop()
        eventListPresenter.onStop()
    }

    override fun setEvents(it: List<EventModel2>?) {
        viewManager = LinearLayoutManager(activity)
        viewAdapter = EventListAdapter(it!!)
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    override fun startNewEventActivity() {
        startActivity(Intent(activity, NewEventActivity::class.java))
    }

}
