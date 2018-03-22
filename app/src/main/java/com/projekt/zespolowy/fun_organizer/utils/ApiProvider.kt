package com.projekt.zespolowy.fun_organizer.utils

import com.projekt.zespolowy.fun_organizer.ping.ApiService
import com.projekt.zespolowy.fun_organizer.ping.PingResponseObject
import com.projekt.zespolowy.fun_organizer.register.UserModel
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
        retrofit = Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        service = retrofit.create(ApiService::class.java)
    }

    private object Holder {
        val INSTANCE = ApiProvider()
    }

    fun postToDatabase(responseObject: PingResponseObject) = service.postToDatabase(responseObject)

    fun postUserToDatabase(user : UserModel) = service.postUserToDatabase(user)

    fun getPing() = service.getResponse()
}