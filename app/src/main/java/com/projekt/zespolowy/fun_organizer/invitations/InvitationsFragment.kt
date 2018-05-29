package com.projekt.zespolowy.fun_organizer.invitations


import android.app.Fragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.projekt.zespolowy.fun_organizer.R
import com.projekt.zespolowy.fun_organizer.utils.ApiProvider
import com.projekt.zespolowy.fun_organizer.utils.SchedulersProvider
import kotlinx.android.synthetic.main.activity_register.view.*
import kotlinx.android.synthetic.main.invitation.*
import kotlinx.android.synthetic.main.invitation.view.*
import kotterknife.bindView

class InvitationsFragment : Fragment(), InvitationListView, InvitationsListener {
    private lateinit var invitationsPresenter: InvitationsPresenter
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private val recyclerView: RecyclerView by bindView(R.id.invitations_recycle_view)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        invitationsPresenter = InvitationsPresenter(InvitationsUseCase(ApiProvider.instance), SchedulersProvider())

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_invitations, container, false)
    }

    override fun onStart() {
        super.onStart()
        invitationsPresenter.onStart(this)
        invitationsPresenter.getUserInvitations()
    }

    override fun onStop() {
        super.onStop()
        invitationsPresenter.onStop(false)
    }

    override fun setEvents(it: List<Invitation>?) {
        viewManager = LinearLayoutManager(activity)
        viewAdapter = InvitationAdapter(it!!, this)
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    override fun onEventClicked(invitation: Invitation, view: View) {
        view.accept
        when (view.id) {
            accept.id -> invitationsPresenter.sentInvitationDesition(invitation.id,2)
            decline.id  ->invitationsPresenter.sentInvitationDesition(invitation.id,1)
        }
    }

    override fun toast(toString: String) {
        Toast.makeText(activity, toString, Toast.LENGTH_LONG).show()
    }

}
