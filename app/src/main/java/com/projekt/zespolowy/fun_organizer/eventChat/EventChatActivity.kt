package com.projekt.zespolowy.fun_organizer.eventChat

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.MenuItem
import com.projekt.zespolowy.fun_organizer.R
import com.projekt.zespolowy.fun_organizer.utils.ApiProvider
import com.projekt.zespolowy.fun_organizer.utils.SchedulersProvider
import kotterknife.bindView

class EventChatActivity : AppCompatActivity(), EventChatView {

    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private val recyclerView: RecyclerView by bindView(R.id.chat_recycler_view)

    private lateinit var eventChatPresenter: EventChatPresenter
    private lateinit var eventID: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_chat)
        eventID = intent.getStringExtra("eventID")
        eventChatPresenter = EventChatPresenter(EventChatUseCase(ApiProvider.instance), SchedulersProvider())
        setActionBar("Event chat")
    }

    override fun onStart() {
        super.onStart()
        eventChatPresenter.getEventChat(eventID.toInt())
        eventChatPresenter.onStart(this)
    }

    override fun onStop() {
        super.onStop()
        eventChatPresenter.onStop()
    }

    override fun setData(it: List<EventChatModel>?) {
        viewManager = LinearLayoutManager(this)
        viewAdapter = ChatAdapter(it!!)
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
        // Respond to the action bar's Up/Home button
            android.R.id.home -> {
                this.finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun setActionBar(heading: String) {
        val actionBar = supportActionBar
        actionBar!!.setHomeButtonEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayShowHomeEnabled(false)
        actionBar.setTitle(heading)
        actionBar.show()
    }

}
