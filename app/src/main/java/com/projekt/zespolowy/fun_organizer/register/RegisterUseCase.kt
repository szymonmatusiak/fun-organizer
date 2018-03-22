package com.projekt.zespolowy.fun_organizer.register

import com.projekt.zespolowy.fun_organizer.utils.ApiProvider

/**
 * Created by viktor on 21.03.2018.
 */

class RegisterUseCase(private val apiProvider: ApiProvider) {
   fun postUserToDatabase(user : UserModel) = apiProvider.postUserToDatabase(user)
}