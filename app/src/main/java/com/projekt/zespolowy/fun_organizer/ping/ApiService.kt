package com.projekt.zespolowy.fun_organizer.ping

import com.projekt.zespolowy.fun_organizer.register.UserModel
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT


/**
 * Created by szymon on 10.03.18.
 */
interface ApiService {
    @GET("/api/ping")
    fun getResponse(): Single<PingResponseObject>

    @POST("/hello/api/pingDB/1")
    fun postToDatabase(@Body responseObject: PingResponseObject): Single<PingResponseObject>

    @PUT("api/user")
    fun postUserToDatabase(user : UserModel) : Single<UserModel>
}