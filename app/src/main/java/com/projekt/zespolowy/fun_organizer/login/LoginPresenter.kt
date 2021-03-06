package com.projekt.zespolowy.fun_organizer.login

import android.preference.PreferenceManager
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
                            var test: String? = it.raw().header("Authorization").toString()
                            if (test?.length!! > 5) {
                                    var sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MyApplication.appContext)
                                sharedPreferences.edit {
                                    putString("Authorization", it.raw().header("Authorization").toString())
                                }
                                //view?.toast(it.raw().header("Authorization").toString())
                                //!!!!!!!! OGARNĄĆ WYCIEKI PAMIĘCI !!!!!!!!
                                checkIfUserIsAuthenticated()
                            } else
                                view?.toast("błędne dane logowania")
                        },
                        {
                            view?.toast("sprawdz połączenie sieciowe")

                        }
                )
    }

    fun checkIfUserIsAuthenticated() {
        loginUseCase.getUserData().
                subscribeOn(schedulersProvider.backgroundThread())
                .observeOn(schedulersProvider.mainThread())
                .subscribe(
                        {
                            var userData = PreferenceManager.getDefaultSharedPreferences(MyApplication.appContext)
                            userData.edit {
                                putString("name", it.name)
                                putString("surname", it.surname)
                                putString("phoneNumber", it.phoneNumber)
                                putString("email", it.email)
                            }
                            view?.checkIfUserIsAuthenticated()
                        },
                        {
                            println(it.toString())
                        }
                )
    }

    fun startNavigationActivity() {
        view?.startNavigationActivity()
    }
}