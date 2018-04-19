package com.projekt.zespolowy.fun_organizer.api

import com.projekt.zespolowy.fun_organizer.eventInfo.EventInfo
import com.projekt.zespolowy.fun_organizer.login.Login
import com.projekt.zespolowy.fun_organizer.newEvent.EventModel
import com.projekt.zespolowy.fun_organizer.register.UserModel
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.*


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
    fun postEventToDatabase(@Body event: EventModel): Single<EventModel>

    @POST("/login")
    fun login(@Body login: Login): Single<Response<Void>>

    @GET("/api/event")
    fun getEventList(): Single<List<com.projekt.zespolowy.fun_organizer.eventList.EventModel2>>

    @GET("/api/event/{id}")
    fun getSingleEvent(@Path("id") eventID: Int): Single<EventInfo>

    @GET("/api/usershowf")
    fun getFriendsList(): Single<List<UserModel>>

    @FormUrlEncoded
    @PUT("/api/useraddfnum")
    fun searchWithPhoneNumber(@Field("number") phonenumber: String): Single<Response<Void>>

    @FormUrlEncoded
    @PUT("/api/useraddf")
    fun searchWithMail(@Field("f") mail: String): Single<Response<Void>>

    @PUT("/api/useraddfbook")
    fun searchWithContactList(@Body list: List<String>): Single<Response<Void>>

}