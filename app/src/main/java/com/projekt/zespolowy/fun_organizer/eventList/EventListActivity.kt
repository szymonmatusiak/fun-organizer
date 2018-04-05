package com.projekt.zespolowy.fun_organizer.eventList

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.projekt.zespolowy.fun_organizer.NewEvent.NewEventActivity
import com.projekt.zespolowy.fun_organizer.R
import com.projekt.zespolowy.fun_organizer.utils.ApiProvider
import com.projekt.zespolowy.fun_organizer.utils.SchedulersProvider
import kotlinx.android.synthetic.main.activity_event_list.*

class EventListActivity : AppCompatActivity(), EventListView {
    private lateinit var eventListPresenter: EventListPresenter
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_list)
        eventListPresenter = EventListPresenter(EventListUseCase(ApiProvider.instance), SchedulersProvider())
    }

    override fun onStart() {
        super.onStart()
        eventListPresenter.onStart(this)
        eventListPresenter.getEventList()

        setSupportActionBar(toolbar)
        new_event_button.setOnClickListener {
            eventListPresenter.startNewActivity()
        }
    }


    override fun onStop() {
        super.onStop()
        eventListPresenter.onStop()
    }

    override fun setEvents(it: List<Event>?) {
        viewManager = LinearLayoutManager(this)
        viewAdapter = EventListAdapter(it!!) //it <- przekazywany JSON z GETa

        recyclerView = findViewById<RecyclerView>(R.id.event_list_recycle_view).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    override fun startNewEventActivity() {
        startActivity(Intent(this, NewEventActivity::class.java))
    }
}
