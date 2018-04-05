package com.projekt.zespolowy.fun_organizer.ping

import com.projekt.zespolowy.fun_organizer.eventList.Event
import com.projekt.zespolowy.fun_organizer.login.Login
import com.projekt.zespolowy.fun_organizer.register.UserModel
import com.projekt.zespolowy.fun_organizer.NewEvent.EventModel
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


/**
 * Created by szymon on 10.03.18.
 */
interface ApiService {
    @GET("/api/ping")
    fun getResponse(): Single<PingResponseObject>

    @POST("/hello/api/pingDB/1")
    fun postToDatabase(@Body responseObject: PingResponseObject): Single<PingResponseObject>

    @POST("api2/user")
    fun postUserToDatabase(@Body user: UserModel): Single<UserModel>

    @POST("api/event")
    fun postEventToDatabase(@Body event: EventModel) :Single<EventModel>

    @POST("/login")
    fun login(@Body login: Login): Single<Response<Void>>

    @GET("/api/event")
    fun getEventList(): Single<List<Event>>
}