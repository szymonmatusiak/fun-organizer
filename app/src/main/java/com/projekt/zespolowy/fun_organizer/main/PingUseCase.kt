package com.projekt.zespolowy.fun_organizer.main

import com.projekt.zespolowy.fun_organizer.login.Login
import com.projekt.zespolowy.fun_organizer.ping.PingResponseObject
import com.projekt.zespolowy.fun_organizer.register.UserModel
import com.projekt.zespolowy.fun_organizer.utils.ApiProvider

/**
 * Created by szymon on 18.03.2018.
 */
class PingUseCase(private val apiProvider: ApiProvider) {

    fun postToDatabase(responseObject: PingResponseObject) = apiProvider.postToDatabase(responseObject)
    fun getPing() = apiProvider.getPing()

    fun postUserToDatabase(user: UserModel) = apiProvider.postUserToDatabase(user)

    fun login(login: Login) = apiProvider.login(login)
}