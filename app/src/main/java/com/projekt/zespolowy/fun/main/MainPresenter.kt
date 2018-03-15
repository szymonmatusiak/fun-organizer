package com.projekt.zespolowy.`fun`.main

/**
 * Created by szymon on 10.03.18.
 */
interface MainPresenter {
    fun onStart(mainView: MainView)
    fun onStop()
    fun getPingResponse()
}