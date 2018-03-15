package com.projekt.zespolowy.`fun`.main

import com.projekt.zespolowy.`fun`.ping.ResponseObject

/**
 * Created by szymon on 10.03.18.
 */
interface MainPresenter {
    fun onStart(mainView: MainView)
    fun onStop()
    fun getPingResponse()
    fun postToDatabase(responseObject: ResponseObject)
}