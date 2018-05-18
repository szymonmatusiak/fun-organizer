package com.projekt.zespolowy.fun_organizer.eventItems

import com.projekt.zespolowy.fun_organizer.eventInfo.Need

interface EventItemsListener {
    fun onEventClicked(item: Need)
}