package com.projekt.zespolowy.fun_organizer.utils

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by szymon on 18.03.2018.
 */
class SchedulersProvider {
    fun mainThread() = AndroidSchedulers.mainThread()!!
    fun backgroundThread() = Schedulers.io()
}