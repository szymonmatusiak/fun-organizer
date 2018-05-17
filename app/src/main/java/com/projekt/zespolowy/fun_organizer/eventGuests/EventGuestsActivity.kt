package com.projekt.zespolowy.fun_organizer.eventGuests

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.projekt.zespolowy.fun_organizer.R
import kotlinx.android.synthetic.main.activity_event_guests.*

class EventGuestsActivity : AppCompatActivity(), EventGuestsView {

    private lateinit var eventGuestsPresenter: EventGuestsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_guests)
        setSupportActionBar(toolbar)
    }

    override fun onStart() {
        super.onStart()
        eventGuestsPresenter.onStart(this)
        var eventID: String = intent.getStringExtra("eventID")
    }

    override fun onStop() {
        super.onStop()
        eventGuestsPresenter.onStop()
    }
}
