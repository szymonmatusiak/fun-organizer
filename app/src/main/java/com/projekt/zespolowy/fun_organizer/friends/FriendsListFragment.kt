package com.projekt.zespolowy.fun_organizer.friends


import android.app.Fragment
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.projekt.zespolowy.fun_organizer.R
import com.projekt.zespolowy.fun_organizer.newFriend.NewFriendActivity
import com.projekt.zespolowy.fun_organizer.register.UserModel
import com.projekt.zespolowy.fun_organizer.utils.ApiProvider
import com.projekt.zespolowy.fun_organizer.utils.SchedulersProvider
import kotterknife.bindView

class FriendsListFragment : Fragment(), FriendsListView {
    private lateinit var friendsListPresenter: FriendsListPresenter
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private val recyclerView: RecyclerView by bindView(R.id.event_list_recycle_view)
    private val newFriendButton: FloatingActionButton by bindView(R.id.new_event_button)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        friendsListPresenter = FriendsListPresenter(FriendsListUseCase(ApiProvider.instance), SchedulersProvider())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_friends, container, false)
    }

    override fun onStart() {
        super.onStart()
        friendsListPresenter.onStart(this)
        friendsListPresenter.getFriendsList()
        newFriendButton.setOnClickListener {
            friendsListPresenter.startNewActivity()
        }
    }


    override fun onStop() {
        super.onStop()
        friendsListPresenter.onStop()
    }

    override fun setFriendsList(it: List<UserModel>?) {
        viewManager = LinearLayoutManager(activity)
        viewAdapter = FriendsListAdapter(it!!)
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    override fun startNewEventActivity() {
        startActivity(Intent(activity, NewFriendActivity::class.java))

    }

}
