package com.projekt.zespolowy.fun_organizer.invitations


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.projekt.zespolowy.fun_organizer.R
import com.projekt.zespolowy.fun_organizer.utils.ApiProvider
import com.projekt.zespolowy.fun_organizer.utils.SchedulersProvider
import kotterknife.bindView

class InvitationsFragment : Fragment(), InvitationListView, InvitationsListener {
    private lateinit var invitationsPresenter: InvitationsPresenter
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private val recyclerView: RecyclerView by bindView(R.id.event_list_recycle_view)
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

    }

    override fun onStop() {
        super.onStop()
        invitationsPresenter.onStop(false)
    }

    override fun onEventClicked(invitation: Invitation, view: View) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
