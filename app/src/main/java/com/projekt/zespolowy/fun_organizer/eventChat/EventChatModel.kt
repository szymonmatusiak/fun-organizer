package com.projekt.zespolowy.fun_organizer.eventChat

import com.projekt.zespolowy.fun_organizer.register.UserModelNoPassword

data class EventChatModel(
        val date: String,
        val poster: UserModelNoPassword,
        val text: String
)