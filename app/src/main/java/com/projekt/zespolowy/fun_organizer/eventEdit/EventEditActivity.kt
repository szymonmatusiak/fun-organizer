package com.projekt.zespolowy.fun_organizer.eventEdit

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.common.api.Api
import com.projekt.zespolowy.fun_organizer.map.MapsActivity
import com.projekt.zespolowy.fun_organizer.utils.ApiProvider
import com.projekt.zespolowy.fun_organizer.utils.SchedulersProvider
import kotlinx.android.synthetic.main.activity_event_edit.*
import kotlinx.android.synthetic.main.activity_new_event.*
import java.util.*

class EventEditActivity : AppCompatActivity(), EventEditView {

    private lateinit var eventPresenter: EventEditPresenter
    private lateinit var eventM: EventModel
    private var currentId: Int = 0
    var itemsList: MutableList<EventNeedsModel> = mutableListOf<EventNeedsModel>()

    var day : String = ""
    var month : String = ""
    var year: String = ""
    var hour: String = ""
    var minutes: String = ""
    var latitude = 0.0
    var longitude = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.projekt.zespolowy.fun_organizer.R.layout.activity_event_edit)
        eventPresenter = EventEditPresenter(EventEditUseCase(ApiProvider.instance), SchedulersProvider())
        eventPresenter.onStart(this)
        var eventID:String = intent.getStringExtra("eventID")
        currentId = Integer.parseInt(eventID)
        eventPresenter.getEventModel(currentId)
    }

    override fun onStart() {
        super.onStart()
        //eventPresenter.onStart(this)
        //var eventID:String = intent.getStringExtra("eventID")
        //toast(eventID)
        //eventPresenter.getEventModel(Integer.parseInt(eventID))

        editDateField.setOnClickListener({
            //Popup calendar
            val now = Calendar.getInstance()
            val datePicker = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(Calendar.YEAR,year)
                selectedDate.set(Calendar.MONTH,month)
                selectedDate.set(Calendar.DAY_OF_MONTH,dayOfMonth)

                this.day = dayOfMonth.toString()
                this.month = month.toString()
                this.year = year.toString()

                if (this.month.length == 1)
                    this.month = "0" + this.month

                if (day.length == 1)
                    day = "0" + day

                editDateField.setText(this.day.toString() + "-" + this.month + "-" + this.year)
            }, now.get(Calendar.YEAR),now.get(Calendar.MONTH),now.get(Calendar.DAY_OF_MONTH))
            datePicker.show()
        })

       editTimeField.setOnClickListener({
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

                editTimeField.setText(this.hour.toString() + ":" + this.minutes)
            }, now.get(Calendar.HOUR_OF_DAY),now.get(Calendar.MINUTE),false)
            timePicker.show()
        })

        editMapBtn.setOnClickListener({
            val i = Intent(this, MapsActivity::class.java)
            startActivityForResult(i,1)
        })

        editBtn.setOnClickListener({
            var finalDate = parseDate()

            eventM = EventModel(
                    editEventName.text.toString(),
            finalDate,
            editPlaceInfo.text.toString(),
            editLocalisation.text.toString(),
            editStreet.text.toString(),
            latitude.toString(),
            longitude.toString(),
            editDescription.text.toString(),
            itemsList)

            eventPresenter.putEventToDatabase(currentId, eventM)
        })
    }

    fun parseDate() : String {
        var finalDate: String = year + "-" + month + "-" + day + " " + hour + ":" + minutes
        return finalDate
    }

    override fun onActivityResult(requestCode : Int, resultCode : Int, data : Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 1) {
            if(resultCode == RESULT_OK) {
                val place = data.getStringExtra("place")
                val address = data.getStringExtra("address")
                latitude = data.getDoubleExtra("latitude", 0.0)
                longitude = data.getDoubleExtra("longitude", 0.0)
                editLocalisation.setText(place)
                editStreet.setText(address)
            }
        }
    }

    override fun toast(text: String) {
        android.widget.Toast.makeText(this, text, android.widget.Toast.LENGTH_LONG).show()
    }

    override fun killActivity() {
        this.finish()
    }



    override fun setEventModel(it: EventModel) {

        val dateAndTime = it.date.split(" ")
        //toast(it.place)
        editEventName.setText(it.name)
        editDateField.setText(dateAndTime[0])
        editTimeField.setText(dateAndTime[1])
        editLocalisation.setText(it.place)
        editStreet.setText(it.address)
        editPlaceInfo.setText(it.placeInfo)
        editDescription.setText(it.description)
        itemsList = it.needs.toMutableList()
    }
}
