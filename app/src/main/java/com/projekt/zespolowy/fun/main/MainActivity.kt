package com.projekt.zespolowy.`fun`.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.projekt.zespolowy.`fun`.R
import com.projekt.zespolowy.`fun`.ping.ResponseObject
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainView {
    private var mainPresenter: MainPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainPresenter = MainPresenterImpl()

    }

    override fun onStart() {
        super.onStart()
        mainPresenter!!.onStart(this)
        button.setOnClickListener {
            mainPresenter!!.getPingResponse()
        }
        buttonPost.setOnClickListener {
            var name = textInput.text.toString()
            mainPresenter!!.postToDatabase(responseObject = ResponseObject(name, 2))
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