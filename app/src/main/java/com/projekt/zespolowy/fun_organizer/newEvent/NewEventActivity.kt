package com.projekt.zespolowy.fun_organizer.newEvent

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.projekt.zespolowy.fun_organizer.R
import com.projekt.zespolowy.fun_organizer.map.MapsActivity
import com.projekt.zespolowy.fun_organizer.utils.ApiProvider
import com.projekt.zespolowy.fun_organizer.utils.SchedulersProvider
import kotlinx.android.synthetic.main.activity_new_event.*
import java.util.*

class NewEventActivity : AppCompatActivity(), NewEventView {

    private lateinit var eventPresenter: NewEventPresenter
    private lateinit var event: EventModel

    private  var latitude: Double = 0.0
    private  var longitude: Double = 0.0

    lateinit var selectedDate : Date
    var day : String = ""
    var month : String = ""
    var year: String = ""
    var hour: String = ""
    var minutes: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_event)
        eventPresenter = NewEventPresenter(NewEventUseCase(ApiProvider.instance), SchedulersProvider())
    }

    override fun onStart() {
        super.onStart()
        eventPresenter.onStart(this)

        //val calend = Calendar.getInstance()

        createBtn.setOnClickListener({
            getValuesFromViewToModel()
            if (!eventPresenter.postEventToDatabase(event)) {
                clearFieldsAfterSendFailure()
            }
        })

        mapBtn.setOnClickListener({
            val i = Intent(this, MapsActivity::class.java)
            startActivityForResult(i, 1)
        })


        dateField.setOnClickListener({
            //Popup calendar
            val now = Calendar.getInstance()
            val datePicker = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(Calendar.YEAR,year)
                selectedDate.set(Calendar.MONTH,month)
                selectedDate.set(Calendar.DAY_OF_MONTH,dayOfMonth)


                //this.selectedDate = selectedDate.da
                this.day = dayOfMonth.toString()
                this.month = month.toString()
                this.year = year.toString()

                if (this.month.length == 1)
                    this.month = "0" + this.month

                if (day.length == 1)
                    day = "0" + day

                dateField.setText(this.day.toString() + "-" + this.month + "-" + this.year)


            }, now.get(Calendar.YEAR),now.get(Calendar.MONTH),now.get(Calendar.DAY_OF_MONTH))
            datePicker.show()

        })

        timeField.setOnClickListener({
            //Popup clock picker
            val now = Calendar.getInstance()
            val timePicker = TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                val selectedTime = Calendar.getInstance()
                selectedTime.set(Calendar.HOUR_OF_DAY,hourOfDay)
                selectedTime.set(Calendar.MINUTE,minute)

                this.hour = hourOfDay.toString()
                this.minutes = minute.toString()

                if (hour.length == 1)
                    hour = "0" + hour

                if (minutes.length == 1)
                    minutes = "0" + minutes

                timeField.setText(this.hour.toString() + ":" + this.minutes)
            }, now.get(Calendar.HOUR_OF_DAY),now.get(Calendar.MINUTE),false)
            timePicker.show()
        })
    }

    override fun onActivityResult(requestCode : Int, resultCode : Int, data : Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 1) {
            if(resultCode == RESULT_OK) {
                val place = data.getStringExtra("place")
                val address = data.getStringExtra("address")
                latitude = data.getDoubleExtra("latitude", 0.0)
                longitude = data.getDoubleExtra("longitude", 0.0)
                localisation.setText(place)
                street.setText(address)
            }
        }
    }

    override fun onStop() {
        super.onStop()
        eventPresenter.onStop()
    }

    fun parseDate(): String {
        if (month.length == 1)
            month = "0" + month

        if (day.length == 1)
            day = "0" + day

        if (hour.length == 1)
            hour = "0" + hour

        if (minutes.length == 1)
            minutes = "0" + minutes

        var finalDate: String = year + "-" + month + "-" + day + " " + hour + ":" + minutes
        return finalDate
    }


    fun getValuesFromViewToModel() {

        //var la:String = latitude.toString().substring(0,5)
        //var lo:String = longitude.toString().substring(0,5)


        var finalDate: String = parseDate()
        event = EventModel(
                eventName.text.toString(),
                finalDate,
                placeInfo.text.toString(),
                localisation.text.toString(),
                street.text.toString(),
                latitude,
                longitude,
                eventDescription.text.toString()
        )
    }

    fun clearFieldsAfterSendFailure() {
        eventName.text.clear()
        localisation.text.clear()
    }


    override fun toast(text: String) {
        android.widget.Toast.makeText(this, text, android.widget.Toast.LENGTH_LONG).show()
    }

    override fun killActivity() {
        this.finish()
    }

}
