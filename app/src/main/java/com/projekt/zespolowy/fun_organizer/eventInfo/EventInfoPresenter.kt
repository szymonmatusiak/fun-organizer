package com.projekt.zespolowy.fun_organizer.eventInfo

import com.projekt.zespolowy.fun_organizer.base.BasePresenter
import com.projekt.zespolowy.fun_organizer.utils.SchedulersProvider

class EventInfoPresenter(
        private val eventInfoUseCase: EventInfoUseCase,
        private val schedulersProvider: SchedulersProvider) : BasePresenter<EventInfoView>() {

    fun onStart(eventInfoView: EventInfoView) {
        attachView(eventInfoView)
    }

    fun onStop() {
        detachView(false)
    }
}