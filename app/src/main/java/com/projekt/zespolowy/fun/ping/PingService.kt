package com.projekt.zespolowy.`fun`.ping

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


/**
 * Created by szymon on 10.03.18.
 */
interface PingService {
    @GET("/hello/api/ping")
    fun getResponse(): Call<ResponseObject>

    @POST("/hello/api/pingDB/1")
    fun postToDatabase(@Body responseObject: ResponseObject): Call<ResponseObject>
}