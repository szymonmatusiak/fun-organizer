package com.projekt.zespolowy.fun_organizer.register

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.projekt.zespolowy.fun_organizer.R
import com.projekt.zespolowy.fun_organizer.utils.ApiProvider
import com.projekt.zespolowy.fun_organizer.utils.SchedulersProvider
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity(), RegisterView {

    private lateinit var registerPresenter: RegisterPresenter
    private lateinit var user: UserModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        registerPresenter = RegisterPresenter(RegisterUseCase(ApiProvider.instance), SchedulersProvider())
    }

    override fun onStart() {
        super.onStart()
        registerPresenter.onStart(this)
        registerButton.setOnClickListener({
            getValuesFromViewToModel()
            if (registerPresenter.postUserToDatabase(user, password2.text.toString()) == false) {
                clearPasswordFieldsAfterSendFailure()
            }
        })
    }

    override fun onStop() {
        super.onStop()
        registerPresenter.onStop()
    }

    fun getValuesFromViewToModel() {
        user = UserModel(email.text.toString(),
                name.text.toString(),
                password1.text.toString(),
                phone.text.toString(),
                surname.text.toString(),
                surname.text.toString())
    }

    fun clearPasswordFieldsAfterSendFailure() {
        password1.text.clear()
        password2.text.clear()

    }


    override fun toast(text: String) {
        android.widget.Toast.makeText(this, text, android.widget.Toast.LENGTH_LONG).show()
    }
}
