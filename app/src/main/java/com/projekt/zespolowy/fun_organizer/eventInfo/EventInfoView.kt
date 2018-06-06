package com.projekt.zespolowy.fun_organizer.eventInfo

import com.projekt.zespolowy.fun_organizer.base.IBaseView

interface EventInfoView : IBaseView {
    fun setEvnetInfo(it: EventInfo)
    fun iAmHost(bool: Boolean)
    fun toast(msg: String)

}