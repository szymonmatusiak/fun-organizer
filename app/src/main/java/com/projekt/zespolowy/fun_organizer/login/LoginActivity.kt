package com.projekt.zespolowy.fun_organizer.login

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.projekt.zespolowy.fun_organizer.R
import kotlinx.android.synthetic.main.activity_login2.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login2)
    }

    override fun onStart() {
        super.onStart()
        loginButton.setOnClickListener { toast(getMessage()) }
    }

    fun getMessage(): String {
        return "(nie)Bezpieczne logowanie:\nLogin: " + loginText.text + "\nHasło: " + passText.text
    }

    fun Context.toast(message: CharSequence) =
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

}
