package com.projekt.zespolowy.fun_organizer.login

import android.app.Activity
import android.widget.Toast

/**
 * Created by Marcin Całkowski on 20.03.2018.
 * Tutaj będą (może) funkcje logowania
 */
class LoginFunctions {

    fun Activity.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, message, duration).show()
    }


}