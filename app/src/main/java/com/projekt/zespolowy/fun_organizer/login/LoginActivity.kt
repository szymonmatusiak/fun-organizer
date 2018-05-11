package com.projekt.zespolowy.fun_organizer.login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.projekt.zespolowy.fun_organizer.MyApplication
import com.projekt.zespolowy.fun_organizer.R
import com.projekt.zespolowy.fun_organizer.navigation.NavigationActivity
import com.projekt.zespolowy.fun_organizer.register.RegisterActivity
import com.projekt.zespolowy.fun_organizer.user.UserData
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
        var editor :SharedPreferences.Editor = getSharedPreferences("userData", MODE_PRIVATE).edit()
        editor.putString("email", loginEditText.text.toString())
        editor.apply()

        val navigatorActivity = Intent(this, NavigationActivity::class.java)
        //navigatorActivity.putExtra("email", loginEditText.id.toString())
        startActivity(navigatorActivity)
        this.finish()
    }

    override fun saveUserData(it: UserData) {
        //toast(it.name + " ; " + it.surname + " ; " + it.email + " ; " + it.phoneNumber + " ; ")
        var editor : SharedPreferences.Editor
        editor = getSharedPreferences("userData", Context.MODE_PRIVATE).edit()
        editor.putString("name", it.name)
        editor.putString("surname", it.surname)
        editor.putString("email", it.email)
        editor.putString("phoneNumber", it.phoneNumber)
        editor.apply()
    }

    override fun toastMessage(): String {
        return "Bezpieczne logowanie:\nLogin: " + loginEditText.text + "\nHasło: " + passwordEditText.text
    }

}
