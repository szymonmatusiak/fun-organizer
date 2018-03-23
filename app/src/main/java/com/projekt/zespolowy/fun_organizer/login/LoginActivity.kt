package com.projekt.zespolowy.fun_organizer.login

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.projekt.zespolowy.fun_organizer.R
import com.projekt.zespolowy.fun_organizer.main.PingUseCase
import com.projekt.zespolowy.fun_organizer.utils.ApiProvider
import com.projekt.zespolowy.fun_organizer.utils.SchedulersProvider
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginView {
    private lateinit var loginPresenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loginPresenter = LoginPresenter(PingUseCase(ApiProvider.instance), SchedulersProvider())
    }

    override fun onStart() {
        super.onStart()
        loginPresenter.onStart(this)
        loginButton.setOnClickListener { login() }
    }

    override fun toast(text: String){
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    private fun login(){
        loginPresenter.login(Login(loginEditText.text.toString(),passwordEditText.text.toString()))
    }

    override fun onStop() {
        super.onStop()
        loginPresenter.onStop()
    }

    override fun startNewActivity() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        //odpalenie okna po pozytywnym zalogowaniu?
    }

    override fun toastMessage(): String {
        return "(nie)Bezpieczne logowanie:\nLogin: " + loginEditText.text + "\nHasło: " + passwordEditText.text
    }
}
