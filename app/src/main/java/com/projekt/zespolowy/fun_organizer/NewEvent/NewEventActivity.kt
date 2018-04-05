package com.projekt.zespolowy.fun_organizer.NewEvent

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.projekt.zespolowy.fun_organizer.R
import com.projekt.zespolowy.fun_organizer.utils.ApiProvider
import com.projekt.zespolowy.fun_organizer.utils.SchedulersProvider
import kotlinx.android.synthetic.main.activity_new_event.*

class NewEventActivity : AppCompatActivity(), NewEventView {

    private lateinit var eventPresenter: NewEventPresenter
    private lateinit var event : EventModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_event)
        eventPresenter= NewEventPresenter(NewEventUseCase(ApiProvider.instance), SchedulersProvider())
    }

    override fun onStart() {
        super.onStart()
        eventPresenter.onStart(this)
        createBtn.setOnClickListener({
            getValuesFromViewToModel()
            if (eventPresenter.postEventToDatabase(event) == false) {
                clearFieldsAfterSendFailure()
            }
        })
    }

    override fun onStop() {
        super.onStop()
        eventPresenter.onStop()
    }

    fun parseDate() : String {
        var day : Int
        var month : Int
        var year : Int
        day = datePick.dayOfMonth
        month = datePick.month
        year = datePick.year
        var finalDate : String = Integer.toString(year) + "-" + Integer.toString(month) + "-" + Integer.toString(day)
        return finalDate
    }

    fun getValuesFromViewToModel() {
        var finalDate : String = parseDate()
        event = EventModel(finalDate,
                eventName.text.toString(),
                localisation.text.toString())
    }

    fun clearFieldsAfterSendFailure() {
        eventName.text.clear()
        localisation.text.clear()
    }
    override fun toast(text: String) {
        android.widget.Toast.makeText(this, text, android.widget.Toast.LENGTH_LONG).show()
    }


}
