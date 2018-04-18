package com.projekt.zespolowy.fun_organizer.eventInfo


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.projekt.zespolowy.fun_organizer.R
import com.projekt.zespolowy.fun_organizer.utils.ApiProvider
import com.projekt.zespolowy.fun_organizer.utils.SchedulersProvider

/**
 * A simple [Fragment] subclass.
 *
 */
class EventInfoFragment : Fragment(), EventInfoView {

    private lateinit var eventInfoPresenter: EventInfoPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        eventInfoPresenter = EventInfoPresenter(EventInfoUseCase(ApiProvider.instance), SchedulersProvider())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_event_info, container, false)
    }

    override fun onStart() {
        super.onStart()
        eventInfoPresenter.onStart(this)

    }

    override fun onStop() {
        super.onStop()
        eventInfoPresenter.onStop()
    }
}
