package com.projekt.zespolowy.fun_organizer.navigation

import com.projekt.zespolowy.fun_organizer.utils.ApiProvider

class NavigationActivityUseCase(private val apiProvider: ApiProvider) {
    fun getUserData() = apiProvider.getUserInfo()
}