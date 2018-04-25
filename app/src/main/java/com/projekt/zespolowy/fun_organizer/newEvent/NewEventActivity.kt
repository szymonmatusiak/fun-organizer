package com.projekt.zespolowy.fun_organizer.newEvent

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.InputType
import android.widget.EditText
import com.projekt.zespolowy.fun_organizer.R
import com.projekt.zespolowy.fun_organizer.utils.ApiProvider
import com.projekt.zespolowy.fun_organizer.utils.SchedulersProvider
import kotlinx.android.synthetic.main.activity_new_event.*
import java.util.*


class NewEventActivity : AppCompatActivity(), NewEventView {

    private lateinit var eventPresenter: NewEventPresenter
    private lateinit var event: EventModel

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    var itemsList: MutableList<EventNeedsModel> = mutableListOf<EventNeedsModel>()
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
        viewAdapter = EventNeedsAdapter(itemsList)

        recyclerView = findViewById<RecyclerView>(R.id.event_needs_recycle_view).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    override fun onStart() {
        super.onStart()
        eventPresenter.onStart(this)

        createBtn.setOnClickListener({
            getValuesFromViewToModel()
            if (!eventPresenter.postEventToDatabase(event)) {
                clearFieldsAfterSendFailure()
            }
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

        //Dodanie do itemu do listy
        add_item_button.setOnClickListener({
            var itemName : String = ""
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Add new item")

            val input = EditText(this)
            input.inputType = InputType.TYPE_CLASS_TEXT
            input.hint = "Item name"

            builder.setView(input)

            builder.setPositiveButton("Add item", DialogInterface.OnClickListener { dialog, which -> itemName = input.text.toString()
                if (itemName != ""){
                    this.itemsList.add(EventNeedsModel("descrioption",false,0,mutableListOf<EventNeedsModel>(),itemName))
                    viewAdapter.notifyItemInserted(itemsList.size - 1)
                }
            })
            builder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })
            builder.show()
        })
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
        event = EventModel(finalDate,
                eventDescription.text.toString(),
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

    override fun killActivity() {
        this.finish()
    }

}
