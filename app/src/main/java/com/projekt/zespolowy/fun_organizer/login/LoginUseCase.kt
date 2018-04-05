package com.projekt.zespolowy.fun_organizer.login

import com.projekt.zespolowy.fun_organizer.utils.ApiProvider

/**
 * Created by szymon on 05.04.18.
 */
class LoginUseCase(private val apiProvider: ApiProvider) {
    fun login(loginCredentials: Login) = apiProvider.login(loginCredentials)
}