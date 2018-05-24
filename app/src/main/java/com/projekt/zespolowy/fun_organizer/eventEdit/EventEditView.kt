package com.projekt.zespolowy.fun_organizer.eventEdit

import com.projekt.zespolowy.fun_organizer.base.IBaseView

/**
 * Created by viktor on 23.05.2018.
 */

interface EventEditView: IBaseView {
    fun toast(text: String)
    fun killActivity()
    fun setEventModel(it: EventModel)
}
