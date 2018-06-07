package com.projekt.zespolowy.fun_organizer.friendsListEvent

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import com.projekt.zespolowy.fun_organizer.R
import com.projekt.zespolowy.fun_organizer.friends.FriendsListUseCase
import com.projekt.zespolowy.fun_organizer.navigation.NavigationActivity
import com.projekt.zespolowy.fun_organizer.register.UserModel
import com.projekt.zespolowy.fun_organizer.utils.ApiProvider
import com.projekt.zespolowy.fun_organizer.utils.SchedulersProvider
import kotlinx.android.synthetic.main.activity_add_friends_to_event.*
import kotterknife.bindView

class AddFriendsToEventActivity : AppCompatActivity(), AddFriendsToEventView, UserClickListener {
    private lateinit var presenter: FriendsEventPresenter
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private val recyclerView: RecyclerView by bindView(R.id.recyclerViewFriendsEvent)
    private lateinit var eventID: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_friends_to_event)
        presenter = FriendsEventPresenter(FriendsListUseCase(ApiProvider.instance), SchedulersProvider())
        eventID = intent.getStringExtra("eventID")
        Log.v("eventIDinMy", eventID)

        setActionBar("Add friends to event")
    }

    override fun onStart() {
        super.onStart()
        presenter.onStart(this)
        presenter.getFriendsList()
        button.setOnClickListener {
            presenter.sendList()
        }
        presenter.saveEventID(eventID)

    }

    override fun setFriendsList(it: List<UserModel>?) {
        viewManager = LinearLayoutManager(this)
        viewAdapter = AddFriendsListAdapter(it!!, this)
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    override fun addToList(user: UserModel) {
        presenter.addToList(user)
    }

    override fun removeFromList(user: UserModel) {
        presenter.removeFromList(user)
    }

    override fun toast(toString: String) {
        Toast.makeText(this, toString, Toast.LENGTH_LONG).show()
    }

    override fun closeActivity() {
        startActivity(Intent(this, NavigationActivity::class.java))
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
