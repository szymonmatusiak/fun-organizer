package com.projekt.zespolowy.fun_organizer.navigation

import android.preference.PreferenceManager
import androidx.core.content.edit
import com.projekt.zespolowy.fun_organizer.MyApplication
import com.projekt.zespolowy.fun_organizer.utils.SchedulersProvider

class NavigatorViewPresenter(private val navigationActivityUseCase: NavigationActivityUseCase,
                             private val schedulersProvider: SchedulersProvider) {
    //Pobranie danych usera gdy ich nie ma
    fun getUserData(){
        navigationActivityUseCase.getUserData().
                subscribeOn(schedulersProvider.backgroundThread())
                .observeOn(schedulersProvider.mainThread())
                .subscribe(
                        {
                            var user = PreferenceManager.getDefaultSharedPreferences(MyApplication.appContext)
                            user.edit {
                                putString("surname", it.surname)
                                putString("name", it.name)
                                putString("phoneNumber", it.phoneNumber)
                                putString("email", it.email)
                            }
                        },
                        {
                            println(it.toString())
                        }
                )
    }

}