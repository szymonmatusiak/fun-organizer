package com.projekt.zespolowy.fun_organizer.main

import com.projekt.zespolowy.fun_organizer.base.IBaseView

/**
 * Created by szymon on 10.03.18.
 */
interface MainView : IBaseView {
    fun toast(text: String)
    fun checkIfUserIsAuthenticated()
    //fun startEventListActivity()
}