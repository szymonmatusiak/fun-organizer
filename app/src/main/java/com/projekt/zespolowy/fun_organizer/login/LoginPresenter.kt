package com.projekt.zespolowy.fun_organizer.login

import android.preference.PreferenceManager
import android.util.Log
import androidx.core.content.edit
import com.projekt.zespolowy.fun_organizer.MyApplication
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
                            if (it.raw().header("Authorization").toString() != null) {
                                var sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MyApplication.appContext)
                                sharedPreferences.edit {
                                    putString("Authorization", it.raw().header("Authorization").toString())
                                }
                                Log.d("Authorization", it.raw().header("Authorization").toString())
                                view?.toast(it.raw().header("Authorization").toString())
                                checkIfUserIsAuthenticated()
                            }
                        },
                        {
                        }
                )
    }

    fun checkIfUserIsAuthenticated() {
        view?.checkIfUserIsAuthenticated()
    }

    fun startNavigationActivity() {
        view?.startNavigationActivity()
    }
}