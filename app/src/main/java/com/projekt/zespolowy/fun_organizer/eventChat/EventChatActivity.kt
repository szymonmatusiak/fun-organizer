package com.projekt.zespolowy.fun_organizer.eventChat

import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.EditText
import androidx.core.widget.toast
import com.projekt.zespolowy.fun_organizer.R
import com.projekt.zespolowy.fun_organizer.register.UserModelNoPassword
import com.projekt.zespolowy.fun_organizer.utils.ApiProvider
import com.projekt.zespolowy.fun_organizer.utils.SchedulersProvider
import kotlinx.android.synthetic.main.activity_event_chat.*
import kotterknife.bindView

class EventChatActivity : AppCompatActivity(), EventChatView {

    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private val recyclerView: RecyclerView by bindView(R.id.chat_recycler_view)

    private lateinit var eventChatPresenter: EventChatPresenter
    private lateinit var eventID: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_chat)
        eventID = intent.getStringExtra("eventID")
        eventChatPresenter = EventChatPresenter(EventChatUseCase(ApiProvider.instance), SchedulersProvider())
        setActionBar("Event chat")
    }

    override fun onStart() {
        super.onStart()
        eventChatPresenter.getEventChat(eventID.toInt())
        eventChatPresenter.onStart(this)


        send_chat_message.setOnClickListener({
            sendMessage()
        })
    }

    override fun onStop() {
        super.onStop()
        eventChatPresenter.onStop()
    }

    override fun setData(it: List<EventChatModel>?) {
        viewManager = LinearLayoutManager(this)
        viewAdapter = ChatAdapter(it!!)
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    fun sendMessage() {
        var message : String
        val builder = AlertDialog.Builder(this)
        val viewInflated = LayoutInflater.from(this).inflate(R.layout.dialog_send_message, findViewById(android.R.id.content) as ViewGroup, false)
        val chatMessage = viewInflated.findViewById(R.id.input_message) as EditText
        builder.setTitle("Send message")
        builder.setView(viewInflated)

        builder.setPositiveButton("Send", DialogInterface.OnClickListener { dialog, which -> message = chatMessage.text.toString()
            if (message != ""){
                var model = EventChatModel("", UserModelNoPassword("xd", "xdd", "xddd", "xdddd") ,message)
                eventChatPresenter.sendChatMessage(eventID.toInt(), model)
                eventChatPresenter.getEventChat(eventID.toInt())
            }
            else {
                toast("Message cannot be empty")
            }
        })
        builder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })
        builder.show()
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)

    }

    override fun notifyOnUpdate() {
        viewAdapter.notifyDataSetChanged()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
        // Respond to the action bar's Up/Home button
            android.R.id.home -> {
                this.finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun setActionBar(heading: String) {
        val actionBar = supportActionBar
        actionBar!!.setHomeButtonEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayShowHomeEnabled(false)
        actionBar.setTitle(heading)
        actionBar.show()
    }

}
