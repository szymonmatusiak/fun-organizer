package com.projekt.zespolowy.fun_organizer.utils

import android.preference.PreferenceManager.getDefaultSharedPreferences
import com.projekt.zespolowy.fun_organizer.MyApplication
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by szymon on 04.04.18.
 */
class HeaderInterceptor : Interceptor {
    companion object {
        private const val AUTHORIZATION = "Authorization"
    }

    private val defaultSharedPreferences = getDefaultSharedPreferences(MyApplication.appContext)

    override fun intercept(chain: Interceptor.Chain?): Response {

        var request = chain!!.request()
        val authorization = request.headers().get(AUTHORIZATION)
        request = request.newBuilder()
                .addHeader(AUTHORIZATION, getHeaderFromSharedPreferences(AUTHORIZATION)!!)
                .build()

        return chain.proceed(request)
    }

    private fun getHeaderFromSharedPreferences(authorization: String?): String? {
        return defaultSharedPreferences.getString(AUTHORIZATION, authorization)
    }

}