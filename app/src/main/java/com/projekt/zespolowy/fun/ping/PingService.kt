package com.projekt.zespolowy.`fun`.ping

import retrofit2.Call
import retrofit2.http.GET


/**
 * Created by szymon on 10.03.18.
 */
interface PingService {
    @GET("/hello/api/ping")
    fun getResponse(): Call<ResponseObject>
}