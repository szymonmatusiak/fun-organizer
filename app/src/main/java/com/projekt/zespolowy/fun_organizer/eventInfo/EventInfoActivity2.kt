package com.projekt.zespolowy.fun_organizer.eventInfo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.projekt.zespolowy.fun_organizer.R
import com.projekt.zespolowy.fun_organizer.utils.ApiProvider
import com.projekt.zespolowy.fun_organizer.utils.SchedulersProvider
import kotlinx.android.synthetic.main.activity_event_info.*

class EventInfoActivity2 : AppCompatActivity(), EventInfoView {

    private lateinit var eventInfoPresenter: EventInfoPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_info)
        eventInfoPresenter = EventInfoPresenter(EventInfoUseCase(ApiProvider.instance), SchedulersProvider())
    }

    override fun onStart() {
        super.onStart()
        eventInfoPresenter.onStart(this)
        //toast(getIntent().getExtras().getInt("eventID"))
        var eventID: String = intent.getStringExtra("eventID")
        eventInfoPresenter.getEventInfo(Integer.parseInt(eventID)) // <---- TO CRASHUJE
    }

    override fun onStop() {
        super.onStop()
        eventInfoPresenter.onStop()
    }

    override fun setEvnetInfo(it: EventInfo) {
        eventInfo_name_textView.setText(it.name)
        eventInfo_place_textView.setText(it.placeName)
        eventInfo_date_textView.setText(it.date)
    }
}
