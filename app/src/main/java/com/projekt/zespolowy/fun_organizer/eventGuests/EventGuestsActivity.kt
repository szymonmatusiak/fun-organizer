package com.projekt.zespolowy.fun_organizer.eventGuests

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.MenuItem
import androidx.core.view.isVisible
import com.projekt.zespolowy.fun_organizer.R
import com.projekt.zespolowy.fun_organizer.friendsListEvent.AddFriendsToEventActivity
import com.projekt.zespolowy.fun_organizer.register.UserModelNoPassword
import com.projekt.zespolowy.fun_organizer.utils.ApiProvider
import com.projekt.zespolowy.fun_organizer.utils.SchedulersProvider
import kotterknife.bindView

class EventGuestsActivity : AppCompatActivity(), EventGuestsView {
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private val recyclerView: RecyclerView by bindView(R.id.event_guests_recycle_view)
    private val button: FloatingActionButton by bindView(R.id.add_friends_button)

    private lateinit var eventGuestsPresenter: EventGuestsPresenter
    private lateinit var eventID: String
    private var iAmHost: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_guests)
        eventID = intent.getStringExtra("eventID")
        iAmHost = intent.getStringExtra("iAmHost").toBoolean()
        eventGuestsPresenter = EventGuestsPresenter(EventGuestsUseCase(ApiProvider.instance), SchedulersProvider())

        setActionBar("Event guests")
    }

    override fun onStart() {
        super.onStart()
        eventGuestsPresenter.onStart(this)
        eventGuestsPresenter.getEventGuest(eventID)

        if (iAmHost)
            button.setOnClickListener {
                eventGuestsPresenter.startNewActivity(eventID)
            }
        else
            button.isVisible = false
    }

    override fun onStop() {
        super.onStop()
        eventGuestsPresenter.onStop()
    }

    override fun setData(it: List<UserModelNoPassword>?) {
        viewManager = LinearLayoutManager(this)
        viewAdapter = GuestAdapter(it!!)
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    override fun startNewActivity(eventID: String) {
        val intent = Intent(this, AddFriendsToEventActivity::class.java)
        intent.putExtra("eventID", eventID)
        startActivity(intent)
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
