package com.projekt.zespolowy.fun_organizer.eventGuests

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

import com.projekt.zespolowy.fun_organizer.R
import com.projekt.zespolowy.fun_organizer.friendsListEvent.AddFriendsToEventActivity
import com.projekt.zespolowy.fun_organizer.register.UserModelNoPassword
import com.projekt.zespolowy.fun_organizer.utils.ApiProvider
import com.projekt.zespolowy.fun_organizer.utils.SchedulersProvider
import kotlinx.android.synthetic.main.activity_event_guests.*
import kotterknife.bindView

class EventGuestsActivity : AppCompatActivity(), EventGuestsView {
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private val recyclerView: RecyclerView by bindView(R.id.event_guests_recycle_view)
    private val button: FloatingActionButton by bindView(R.id.add_friends_button)

    private lateinit var eventGuestsPresenter: EventGuestsPresenter
    private lateinit var eventID: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_guests)
        eventID = intent.getStringExtra("eventID")
        eventGuestsPresenter = EventGuestsPresenter(EventGuestsUseCase(ApiProvider.instance), SchedulersProvider())
    }

    override fun onStart() {
        super.onStart()
        eventGuestsPresenter.onStart(this)
        eventGuestsPresenter.getEventGuest(eventID)
        button.setOnClickListener {
            eventGuestsPresenter.startNewActivity(eventID)
        }
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

}
