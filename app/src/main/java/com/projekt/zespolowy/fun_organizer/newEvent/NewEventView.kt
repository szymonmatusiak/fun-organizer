package com.projekt.zespolowy.fun_organizer.newEvent

import com.projekt.zespolowy.fun_organizer.base.IBaseView

/**
 * Created by viktor on 05.04.2018.
 */

interface NewEventView : IBaseView {
    fun toast(text : String)
    fun killActivity()
}