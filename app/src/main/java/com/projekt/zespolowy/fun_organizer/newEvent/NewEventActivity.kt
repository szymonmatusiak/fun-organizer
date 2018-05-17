package com.projekt.zespolowy.fun_organizer.newEvent

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.EditText
import com.projekt.zespolowy.fun_organizer.R
import com.projekt.zespolowy.fun_organizer.map.MapsActivity
import com.projekt.zespolowy.fun_organizer.utils.ApiProvider
import com.projekt.zespolowy.fun_organizer.utils.SchedulersProvider
import kotlinx.android.synthetic.main.activity_new_event.*
import java.util.*


class NewEventActivity : AppCompatActivity(), NewEventView, ItemListener {

    private lateinit var eventPresenter: NewEventPresenter
    private lateinit var event: EventModel

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    var itemsList: MutableList<EventNeedsModel> = mutableListOf<EventNeedsModel>()
    private var latitude: Double = 0.0
    private var longitude: Double = 0.0

    //lateinit var selectedDate : Date
    var day : String = ""
    var month : String = ""
    var year: String = ""
    var hour: String = ""
    var minutes: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_event)
        eventPresenter = NewEventPresenter(NewEventUseCase(ApiProvider.instance), SchedulersProvider())

        viewManager = LinearLayoutManager(this)
        viewAdapter = EventNeedsAdapter(itemsList, this)

        recyclerView = findViewById<RecyclerView>(R.id.event_needs_recycle_view).apply {
            setHasFixedSize(false)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    override fun onStart() {
        super.onStart()
        eventPresenter.onStart(this)

        createBtn.setOnClickListener({
            getValuesFromViewToModel()
            //toast(event.toString())
            if (!eventPresenter.postEventToDatabase(event)) {
                //clearFieldsAfterSendFailure()
            }
            //toast(event.toString())
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

        add_item_button.setOnClickListener({
            var itemName : String
            val builder = AlertDialog.Builder(this)
            val viewInflated = LayoutInflater.from(this).inflate(R.layout.dialog_add_item, findViewById(android.R.id.content) as ViewGroup, false)
            val inputItem = viewInflated.findViewById(R.id.input_item) as EditText
            val inputDescription = viewInflated.findViewById(R.id.input_description) as EditText
            builder.setTitle("Add item")
            builder.setView(viewInflated)

            builder.setPositiveButton("Add", DialogInterface.OnClickListener { dialog, which -> itemName = inputItem.text.toString()
                if (itemName != ""){
                    this.itemsList.add(EventNeedsModel(itemName,inputDescription.text.toString()))
                    viewAdapter.notifyItemInserted(itemsList.size - 1)
                }
            })
            builder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })
            builder.show()
            this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        })
    }

    override fun onDeleteClicked(data: EventNeedsModel) {
        var index = itemsList.indexOf(data)
        itemsList.removeAt(index)
        viewAdapter.notifyItemRemoved(index)
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
        //Data powinna byc zmieniona wczesniej
       /* if (month.length == 1)
            month = "0" + month

        if (day.length == 1)
            day = "0" + day

        if (hour.length == 1)
            hour = "0" + hour

        if (minutes.length == 1)
            minutes = "0" + minutes*/

        var finalDate: String = year + "-" + month + "-" + day + " " + hour + ":" + minutes
        return finalDate
    }


    fun getValuesFromViewToModel() {
        var finalDate: String = parseDate()
        //var finalList: List<EventNeedsModel> = itemsList

        event = EventModel(
                eventName.text.toString(),
                finalDate,
                placeInfo.text.toString(),
                localisation.text.toString(),
                street.text.toString(),
                latitude.toString(),
                longitude.toString(),
                eventDescription.text.toString(),
                itemsList
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
