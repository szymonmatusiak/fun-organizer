package com.projekt.zespolowy.`fun`.main

import com.projekt.zespolowy.`fun`.base.BasePresenter
import com.projekt.zespolowy.`fun`.ping.PingService
import com.projekt.zespolowy.`fun`.ping.ResponseObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainPresenterImpl : BasePresenter<MainView>(), MainPresenter {
    var API_BASE_URL = "http://156.17.41.236:8080"
    val OK = 200

    var retrofit: Retrofit? = null
    var service: PingService? = null

    override fun onStart(mainView: MainView) {
        attachView(mainView)
        retrofit = Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        service = retrofit?.create(PingService::class.java)
        //getPingResponse()

    }

    override fun onStop() {
        detachView(false)
    }

    override fun postToDatabase(responseObject: ResponseObject) {
        var ping: Call<ResponseObject> = service!!.postToDatabase(responseObject)
        ping.enqueue(object : Callback<ResponseObject> {
            override fun onFailure(call: Call<ResponseObject>?, t: Throwable?) {
                view?.toast(t.toString() + call.toString())
            }

            override fun onResponse(call: Call<ResponseObject>?, response: Response<ResponseObject>?) {
                var statusCode = response!!.code()
                if (statusCode == OK) {
                    var response: ResponseObject? = response.body()
                    view?.toast(response.toString())
                }
            }
        })
        view?.toast("after")
    }

    override fun getPingResponse() {
        var ping: Call<ResponseObject> = service!!.getResponse()

        ping.enqueue(object : Callback<ResponseObject> {
            override fun onFailure(call: Call<ResponseObject>?, t: Throwable?) {
                view?.toast(t.toString() + call.toString())
            }

            override fun onResponse(call: Call<ResponseObject>?, response: Response<ResponseObject>?) {
                var statusCode = response!!.code()
                if (statusCode == OK) {
                    var response: ResponseObject? = response.body()
                    view?.toast(response.toString())
                }
            }
        })
    }
}