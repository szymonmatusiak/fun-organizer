package com.projekt.zespolowy.fun_organizer.login

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.projekt.zespolowy.fun_organizer.MyApplication
import com.projekt.zespolowy.fun_organizer.R
import com.projekt.zespolowy.fun_organizer.navigation.NavigationActivity
import com.projekt.zespolowy.fun_organizer.register.RegisterActivity
import com.projekt.zespolowy.fun_organizer.utils.ApiProvider
import com.projekt.zespolowy.fun_organizer.utils.SchedulersProvider
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginView {

    private lateinit var loginPresenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_login)
        loginPresenter = LoginPresenter(LoginUseCase(ApiProvider.instance), SchedulersProvider())
    }

    override fun onStart() {
        super.onStart()
        loginPresenter.onStart(this)
        loginPresenter.checkIfUserIsAuthenticated()
        loginButton.setOnClickListener { login() }
        createAccountButton.setOnClickListener { startActivity(Intent(this, RegisterActivity::class.java)) }
    }

    override fun toast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    private fun login() {
        //loginPresenter.login(Login("testear123@gmail.com", "thisIsAPassworda"))
        loginPresenter.login(Login(loginEditText.text.toString(), passwordEditText.text.toString()))

    }

    override fun onStop() {
        super.onStop()
        loginPresenter.onStop()
    }

    override fun checkIfUserIsAuthenticated() {
        lateinit var authorization: String
        var sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MyApplication.appContext)

        authorization = sharedPreferences.getString("Authorization", "not")
        if (authorization != "not" && authorization != "null")
            loginPresenter.startNavigationActivity()
    }

    override fun startNavigationActivity() {
        startActivity(Intent(this, NavigationActivity::class.java))
        this.finish()
    }

    override fun toastMessage(): String {
        return "Bezpieczne logowanie:\nLogin: " + loginEditText.text + "\nHasło: " + passwordEditText.text
    }

}
