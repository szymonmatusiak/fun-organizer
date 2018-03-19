package com.projekt.zespolowy.fun_organizer.main

import com.projekt.zespolowy.fun_organizer.base.BasePresenter
import com.projekt.zespolowy.fun_organizer.ping.PingResponseObject
import com.projekt.zespolowy.fun_organizer.utils.SchedulersProvider

class MainPresenter(
        private val pingUseCase: PingUseCase,
        private val schedulersProvider: SchedulersProvider) : BasePresenter<MainView>() {


    fun onStart(mainView: MainView) {
        attachView(mainView)
    }

    fun onStop() {
        detachView(false)
    }

    fun postToDatabase(responseObject: PingResponseObject) {
        pingUseCase
                .postToDatabase(responseObject)
                .subscribeOn(schedulersProvider.backgroundThread())
                .observeOn(schedulersProvider.mainThread())
                .subscribe(
                        {
                            view?.toast(it.toString())
                        },
                        {
                            view?.toast(it.toString())
                        }
                )
    }

    fun getPingResponse() {
        pingUseCase
                .getPing()
                .subscribeOn(schedulersProvider.backgroundThread())
                .observeOn(schedulersProvider.mainThread())
                .subscribe(
                        {
                            view?.toast(it.toString())
                        },
                        {
                            view?.toast(it.toString())
                        }
                )
    }
}