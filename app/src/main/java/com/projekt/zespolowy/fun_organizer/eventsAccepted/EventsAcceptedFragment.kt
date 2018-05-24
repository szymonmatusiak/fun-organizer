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
import android.widget.Toast
import androidx.core.view.isVisible
import com.projekt.zespolowy.fun_organizer.R
import com.projekt.zespolowy.fun_organizer.eventInfo.EventInfoActivity2
import com.projekt.zespolowy.fun_organizer.newEvent.NewEventActivity
import com.projekt.zespolowy.fun_organizer.utils.ApiProvider
import com.projekt.zespolowy.fun_organizer.utils.SchedulersProvider
import kotterknife.bindView


class EventsAcceptedFragment : Fragment(), EventsAcceptedView, EventListener {

    private lateinit var eventsAcceptedPresenter: EventsAcceptedPresenter
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private val recyclerView: RecyclerView by bindView(R.id.event_list_recycle_view)
    private val newEventButton: FloatingActionButton by bindView(R.id.new_event_button)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        eventsAcceptedPresenter = EventsAcceptedPresenter(EventsAcceptedUseCase(ApiProvider.instance), SchedulersProvider())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_event_list, container, false)
    }

    override fun onStart() {
        super.onStart()
        eventsAcceptedPresenter.onStart(this)
        eventsAcceptedPresenter.getEventsAccepted()

        newEventButton.isVisible = false
        newEventButton.isEnabled = false

        /*newEventButton.setOnClickListener {
            eventsAcceptedPresenter.startNewActivity()
        }
        Toast.makeText(activity,"test",Toast.LENGTH_LONG).show()*/
    }

    override fun onStop() {
        super.onStop()
        eventsAcceptedPresenter.onStop()
    }

    override fun setEvents(it: List<EventModel2>?) {
        viewManager = LinearLayoutManager(activity)
        viewAdapter = EventListAdapter(it!!, this)
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    override fun startNewEventActivity() {
        startActivity(Intent(activity, NewEventActivity::class.java))
    }

    override fun onEventClicked(event: EventModel2) {
        eventsAcceptedPresenter.onEventClicked(event)
    }

    override fun toast(string: String) {
        Toast.makeText(activity, string, Toast.LENGTH_LONG).show()
    }

    override fun startEventInfoActivity(event: EventModel2) {
        val intent = Intent(activity, EventInfoActivity2::class.java)
        intent.putExtra("eventID", event.id.toString())
        startActivity(intent)
    }
}
