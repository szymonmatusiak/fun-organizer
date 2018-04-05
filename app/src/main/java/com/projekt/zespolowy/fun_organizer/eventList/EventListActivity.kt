package com.projekt.zespolowy.fun_organizer.eventList

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.projekt.zespolowy.fun_organizer.R
import com.projekt.zespolowy.fun_organizer.utils.ApiProvider
import com.projekt.zespolowy.fun_organizer.utils.SchedulersProvider
import kotlinx.android.synthetic.main.activity_event_list.*

class EventListActivity : AppCompatActivity(), EventListView {
    private lateinit var eventListPresenter: EventListPresenter

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    //testowa arrayLista
    //val test = Array(5, { i -> (i * i).toString() })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_list)
        eventListPresenter = EventListPresenter(EventListUseCase(ApiProvider.instance), SchedulersProvider())



        //Layout recycle wiev - do ogarnięcia
        viewManager = LinearLayoutManager(this)
        //test.fill("Testx",1,5)
        viewAdapter = EventListAdapter(test) //konkretny obiekt

        recyclerView = findViewById<RecyclerView>(R.id.event_list_recycle_view).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter
        }

            //Rzeczy domyslnego buildera
        setSupportActionBar(toolbar)
        new_event_button.setOnClickListener {
            view ->
            Snackbar.make(view, "Replace with new event activity", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            eventListPresenter.getEventList()
        }
    }

    override fun onStart() {
        super.onStart()
        //EventListPresenter.onStart(this)
        //loginButton.setOnClickListener { login() }
    }

    override fun onStop() {
        super.onStop()
        eventListPresenter.onStop()
    }

    /*override fun startNewActivity() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }*/
}
