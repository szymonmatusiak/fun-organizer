package com.projekt.zespolowy.fun_organizer.api

import com.projekt.zespolowy.fun_organizer.eventInfo.EventInfo
import com.projekt.zespolowy.fun_organizer.eventInfo.NeedNoID
import com.projekt.zespolowy.fun_organizer.eventItems.SingleItemModel
import com.projekt.zespolowy.fun_organizer.eventItems.SingleItemSmallModel
import com.projekt.zespolowy.fun_organizer.friendsListEvent.InviteData
import com.projekt.zespolowy.fun_organizer.invitations.Invitation
import com.projekt.zespolowy.fun_organizer.login.Login
import com.projekt.zespolowy.fun_organizer.newEvent.EventModel
import com.projekt.zespolowy.fun_organizer.newEvent.EventNeedsModel
import com.projekt.zespolowy.fun_organizer.register.UserModel
import com.projekt.zespolowy.fun_organizer.register.UserModelNoPassword
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.*

/**
 * Created by szymon on 10.03.18.
 */
interface ApiService {
    //Ping
    @GET("/api/ping")
    fun getResponse(): Single<PingResponseObject>

    @POST("/hello/api/pingDB/1")
    fun postToDatabase(@Body responseObject: PingResponseObject): Single<PingResponseObject>

    //Login/register
    @POST("api2/user")
    fun postUserToDatabase(@Body user: UserModel): Single<UserModel>

    @POST("/login")
    fun login(@Body login: Login): Single<Response<Void>>

    @GET("/api/tester")
    fun getUserInfo(): Single<UserModelNoPassword>

    //Event
    @POST("api/event")
    fun postEventToDatabase(@Body event: EventModel): Single<EventModel>

    @GET("/api/event")
    fun getEventList(): Single<List<com.projekt.zespolowy.fun_organizer.eventList.EventModel2>>

    @GET("/api/event/{id}")
    fun getSingleEvent(@Path("id") eventID: Int): Single<EventInfo>

    @GET("/api/eventchk")
    fun getIfIsHost(@Query("id") eventID: Int): Single<Boolean>

    @DELETE("/api/event/delmyself")
    fun deleteMyself(@Query("event") eventID: Int): Single<Response<Void>>

    //Friends
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

    //Guests
    @POST("/api/event/invite")
    fun sendInvitationToEvent(@Body inviteData: InviteData): Single<Response<Void>>

    @GET("/api/event/guests")
    fun getEventGuest(@Query("id") eventID: String): Single<List<UserModelNoPassword>>

    //EventNeeds/items
    @POST("/api/itemcat")
    fun postEventCategory(@Body category: EventNeedsModel): Single<Response<Void>>

    @PUT("/api/itemcat")
    fun confirmCategory(@Query("id") groupID: Int, @Body recivedGroup: NeedNoID): Single<Response<Void>>

    @DELETE("/api/itemcat")
    fun deleteItemCategory(@Query("id") itemID: Int): Single<Response<Void>>

    //EventNeeds/Single items
    @GET("/api/itemall")
    fun getAllCategoryItems(@Query("catid") catID: Int): Single<MutableList<SingleItemModel>>

    @POST("/api/item")
    fun postItemToCategory(@Query("catid") catID: Int, @Body recivedItemModel: SingleItemSmallModel): Single<SingleItemSmallModel>

    @DELETE("/api/item")
    fun deleteItemInCategory(@Query("id") itemID: Int): Single<Response<Void>>

    @PUT("/api/item")
    fun editItemInCategory(@Query("id") itemID: Int, @Body recivetItem: SingleItemSmallModel): Single<SingleItemSmallModel>

    @GET("/api/eventgo")
    fun getEventsAccepted(): Single<List<com.projekt.zespolowy.fun_organizer.eventList.EventModel2>>


    @GET("api/usershowi")
    fun getUserInvitations(): Single<List<Invitation>>

    @POST("api/event/inviteresponse")
    fun sentInvitationDesition(
            @Query("invid") id: Int,
            @Query("r") i: Int
    ): Single<Response<Void>>

    @PUT("/api/event")
    fun putEventToDatabase(@Query("eventid") eventID: Int, @Body event: com.projekt.zespolowy.fun_organizer.eventEdit.EventModel): Single<com.projekt.zespolowy.fun_organizer.eventEdit.EventModel>

    @GET("/api/event/{id}")
    fun getEventForEdit(@Path("id") eventID: Int): Single<com.projekt.zespolowy.fun_organizer.eventEdit.EventModel>

}