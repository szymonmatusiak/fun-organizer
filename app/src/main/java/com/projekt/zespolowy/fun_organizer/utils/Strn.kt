package com.projekt.zespolowy.fun_organizer.utils

/**
 * Created by szymon on 18.03.2018.
 */
class Strn {
    fun parseString(s: String) =
            if (s.contains("asd")) {
                "asd"
            } else if (s.isNotBlank() && s.isNotEmpty()) {
                s
            } else {
                ""
            }
}