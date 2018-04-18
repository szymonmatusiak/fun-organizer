package com.projekt.zespolowy.fun_organizer.utils

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.projekt.zespolowy.fun_organizer.api.ApiService
import com.projekt.zespolowy.fun_organizer.api.PingResponseObject
import com.projekt.zespolowy.fun_organizer.login.Login
import com.projekt.zespolowy.fun_organizer.newEvent.EventModel
import com.projekt.zespolowy.fun_organizer.register.UserModel
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 *
 * Created by szymon on 18.03.2018.
 */
class ApiProvider private constructor() {
    companion object {
        private const val API_BASE_URL = "http://156.17.41.236:8080"
        val instance: ApiProvider by lazy { Holder.INSTANCE }
    }

    private val retrofit: Retrofit
    private val service: ApiService

    init {
        var okHttpClient = OkHttpClient.Builder()
                .addNetworkInterceptor(StethoInterceptor())
                .addInterceptor(HeaderInterceptor())
                .build()
        retrofit = Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()

        service = retrofit.create(ApiService::class.java)
    }

    private object Holder {
        val INSTANCE = ApiProvider()
    }

    fun postToDatabase(responseObject: PingResponseObject) = service.postToDatabase(responseObject)

    fun postUserToDatabase(user: UserModel) = service.postUserToDatabase(user)
    fun postEventToDatabase(event: EventModel) = service.postEventToDatabase(event)

    fun getPing() = service.getResponse()

    fun login(login: Login) = service.login(login)

    fun getEventList() = service.getEventList()
    fun getSingleEvent(eventID : Int) = service.getSingleEvent(eventID)
    fun getFriendsList() = service.getFriendsList()

}