package com.projekt.zespolowy.fun_organizer.eventInfo

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.widget.toast
import com.projekt.zespolowy.fun_organizer.R
import com.projekt.zespolowy.fun_organizer.eventGuests.EventGuestsActivity
import com.projekt.zespolowy.fun_organizer.eventItems.EventItemsActivity
import com.projekt.zespolowy.fun_organizer.utils.ApiProvider
import com.projekt.zespolowy.fun_organizer.utils.SchedulersProvider
import kotlinx.android.synthetic.main.activity_event_info.*

class EventInfoActivity2 : AppCompatActivity(), EventInfoView {

    private lateinit var eventInfoPresenter: EventInfoPresenter
    private var iAmHost: Boolean = false

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
            toast("Jestem hostem i mogę to klikać :)")
            //Przycisk do edycji <<<
            /*val intent = Intent(this, TutajNazwaKlasyXD::class.java)
            intent.putExtra("eventID", eventID.toString())
            startActivity(intent)*/
        })

        eventInfo_show_needs.setOnClickListener({
            //trzeba chyba przekazać jako extra intent czy jestem hostem
            val intent = Intent(this, EventItemsActivity::class.java)
            intent.putExtra("eventID", eventID.toString())
            startActivity(intent)
        })

        eventInfo_show_guests.setOnClickListener({
            //trzeba chyba przekazać jako extra intent czy jestem hostem
            val intent = Intent(this, EventGuestsActivity::class.java)
            intent.putExtra("eventID", eventID.toString())
            startActivity(intent)
        })
    }

    override fun onStop() {
        super.onStop()
        eventInfoPresenter.onStop()
    }

    override fun setEvnetInfo(it: EventInfo) {
        //toast(it.toString())
        eventInfo_name_textView.text = it.name

        var author: String

        eventInfo_author_textView.text = it.host.name + it.host.surname + " (" +  it.host.email + ")"

        eventInfo_place_textView.text = it.place
        if (eventInfo_place_textView.text.equals("") || eventInfo_place_textView.text.contains("°")){
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

    override fun iAmHost(bool: Boolean){
        this.iAmHost = bool
        // bool to przesłanie czy jestem hostem czy nie
        // okeśla to widzoczność i dla bepeczeństwa możliwość klikania przycisku
        // tylko host wydażenia powinien je widzieć
        event_details_edit_event.isEnabled = bool
        event_details_edit_event.isVisible = bool
    }

    fun onCreateDialog(): Dialog {

        //builder dialogu
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Pokazać mapę? (nie wiem czy dać dialog czy odrazu przenieść, no i nie ma lepszej ikonki w domyślnych :f)")
                .setPositiveButton("Tak", DialogInterface.OnClickListener { dialog, id ->
                    // Odpal mapkę
                })
                .setNegativeButton("Nie", DialogInterface.OnClickListener { dialog, id ->
                    // Anuluj
                })
        return builder.create()
    }
}
