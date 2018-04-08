package com.projekt.zespolowy.fun_organizer.login

import com.projekt.zespolowy.fun_organizer.base.BasePresenter
import com.projekt.zespolowy.fun_organizer.utils.SchedulersProvider

/**
 * Created by Marcin Całkowski on 20.03.2018.
 * Presenter for login activity
 */
class LoginPresenter(
        private val loginUseCase: LoginUseCase,
        private val schedulersProvider: SchedulersProvider) : BasePresenter<LoginView>() {

    fun onStart(loginView: LoginView) {
        attachView(loginView)
    }

    fun onStop() {
        detachView(false)
    }

    fun login(login: Login) {
        loginUseCase
                .login(login)
                .subscribeOn(schedulersProvider.backgroundThread())
                .observeOn(schedulersProvider.mainThread())
                .subscribe(
                        {
                            view?.startNewActivity()
                        },
                        {
                            view?.toast("Błędne dane logowania")
                        }
                )
    }
}