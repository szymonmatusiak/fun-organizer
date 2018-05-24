package com.projekt.zespolowy.fun_organizer.eventInfo

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import androidx.core.view.isVisible
import androidx.core.widget.toast
import com.projekt.zespolowy.fun_organizer.R
import com.projekt.zespolowy.fun_organizer.eventEdit.EventEditActivity
import com.projekt.zespolowy.fun_organizer.eventGuests.EventGuestsActivity
import com.projekt.zespolowy.fun_organizer.eventItems.EventItemsActivity
import com.projekt.zespolowy.fun_organizer.utils.ApiProvider
import com.projekt.zespolowy.fun_organizer.utils.SchedulersProvider
import kotlinx.android.synthetic.main.activity_event_info.*

class EventInfoActivity2 : AppCompatActivity(), EventInfoView, EventInfoItemsGoupsListener {

    private lateinit var eventInfoPresenter: EventInfoPresenter
    private var iAmHost: Boolean = false

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var eventInfo: EventInfo
    var groupsList: MutableList<Need> = mutableListOf<Need>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_info)
        eventInfoPresenter = EventInfoPresenter(EventInfoUseCase(ApiProvider.instance), SchedulersProvider())
    }

    override fun onStart() {
        super.onStart()
        eventInfoPresenter.onStart(this)
        var eventID: String = intent.getStringExtra("eventID")
        eventInfoPresenter.getIfIsHost(Integer.parseInt(eventID))
        eventInfoPresenter.getEventInfo(Integer.parseInt(eventID))

        imageView_show_on_map.setOnClickListener({
            //Przycisk mapy tutaj <<<
            var builder = onCreateDialog()
            builder.show()
        })

        event_details_edit_event.setOnClickListener({
            //Przycisk do edycji <<<
            val intent = Intent(this, EventEditActivity::class.java)
            intent.putExtra("eventID", eventID.toString())
            startActivity(intent)
        })

        event_info_add_item_button.setOnClickListener({
            toast("Jako host mogę dodawać rzeczy :)")
        })

        /*eventInfo_show_needs.setOnClickListener({
            //trzeba chyba przekazać jako extra intent czy jestem hostem
            val intent = Intent(this, EventItemsActivity::class.java)
            intent.putExtra("eventID", eventID.toString())
            startActivity(intent)
        })*/

        eventInfo_show_guests.setOnClickListener({
            //trzeba chyba przekazać jako extra intent czy jestem hostem
            val intent = Intent(this, EventGuestsActivity::class.java)
            intent.putExtra("eventID", eventID)

            startActivity(intent)
        })
    }

    override fun onStop() {
        super.onStop()
        eventInfoPresenter.onStop()
    }

    override fun setEvnetInfo(it: EventInfo) {
        eventInfo = it
        groupsList = it.needs.toMutableList()
        groupsList.sortBy { it.id }

        viewManager = LinearLayoutManager(this)
        viewAdapter = EventInfoItemsGroupsAdapter(groupsList, iAmHost, this)

        recyclerView = findViewById<RecyclerView>(R.id.event_info_event_needs_recycle_view).apply {
            setHasFixedSize(false)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        if (groupsList.isEmpty()) {
            event_info_eventNeedsLabel.isVisible = false
        }

        eventInfo_name_textView.text = it.name

        var author = ""
        if (it.host.name.equals("") && it.host.surname.equals(""))
            author = it.host.email
        else
            author = it.host.name + " " + it.host.surname + " (" + it.host.email + ")"
        eventInfo_author_textView.text = author

        eventInfo_place_textView.text = it.place
        if (eventInfo_place_textView.text.equals("") || eventInfo_place_textView.text.contains("°")) {
            eventInfo_place_textView.isVisible = false
        }
        eventInfo_address_textView.text = it.address
        if (eventInfo_address_textView.text.equals(""))
            eventInfo_address_textView.isVisible = false
        eventInfo_placeInfo_textView.text = it.placeInfo
        eventInfo_date_textView.text = it.date
        eventInfo_description_textView.text = it.description
        if (eventInfo_description_textView.text.equals(""))
            eventInfo_description_text_textView.isVisible = false;
    }

    override fun iAmHost(bool: Boolean) {
        this.iAmHost = bool
        // bool to przesłanie czy jestem hostem czy nie
        // okeśla to widzoczność i dla bepeczeństwa możliwość klikania przycisku
        // tylko host wydażenia powinien je widzieć
        event_details_edit_event.isEnabled = bool
        event_details_edit_event.isVisible = bool
        event_info_add_item_button.isEnabled = bool
        event_info_add_item_button.isVisible = bool
    }

    override fun onEventClicked(item: Need) {
        //Na kliknętą grupe...
        val intent = Intent(this, EventItemsActivity::class.java)
        intent.putExtra("groupID", item.id.toString())
        intent.putExtra("itemName", item.name)
        intent.putExtra("description", item.description)
        intent.putExtra("enough", item.enough.toString())
        intent.putExtra("iAmHost", iAmHost.toString())

        startActivity(intent)
    }

    fun onCreateDialog(): Dialog {

        //builder dialogu
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Show map?")
                .setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, id ->
                    startActivity(Intent(Intent.ACTION_VIEW,
                            Uri.parse(String.format("http://maps.google.co.in/maps?q=geo:%s,%s(%s)", eventInfo.latitude, eventInfo.longitude,eventInfo.address))))
                })
                .setNegativeButton("No", DialogInterface.OnClickListener { dialog, id ->
                    // Anuluj
                })
        return builder.create()
    }

}
