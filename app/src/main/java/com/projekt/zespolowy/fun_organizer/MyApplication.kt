package com.projekt.zespolowy.fun_organizer

import android.app.Application
import android.content.Context
import com.facebook.stetho.Stetho


/**
 * Created by szymon on 04.04.18.
 */

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        Stetho.initializeWithDefaults(appContext)

    }

    companion object {
        lateinit var appContext: Context
    }

}