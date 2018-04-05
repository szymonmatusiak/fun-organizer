package com.projekt.zespolowy.fun_organizer.login

import com.projekt.zespolowy.fun_organizer.base.IBaseView

/**
 * Created by Marcin Całkowski on 22.03.2018.
 * Login view interface
 */
interface LoginView : IBaseView {
    fun toast(text: String)
    fun startNewActivity()
    fun toastMessage(): String
}