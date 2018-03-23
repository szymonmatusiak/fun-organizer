package com.projekt.zespolowy.fun_organizer.main

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.projekt.zespolowy.fun_organizer.R
import com.projekt.zespolowy.fun_organizer.register.RegisterActivity
import com.projekt.zespolowy.fun_organizer.utils.ApiProvider
import com.projekt.zespolowy.fun_organizer.utils.SchedulersProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainView {

    private lateinit var mainPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainPresenter = MainPresenter(PingUseCase(ApiProvider.instance), SchedulersProvider())
    }

    override fun onStart() {
        super.onStart()
        mainPresenter.onStart(this)
        pingButton.setOnClickListener { mainPresenter.getPingResponse() }
        registerButton.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    override fun onStop() {
        super.onStop()
        mainPresenter!!.onStop()
    }

    override fun toast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }

}