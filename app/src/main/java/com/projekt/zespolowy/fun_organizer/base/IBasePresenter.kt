package com.projekt.zespolowy.fun_organizer.base

/**
 * Created by szymon on 10.03.18.
 */
interface IBasePresenter<V : IBaseView> {
    fun attachView(view: V)
    fun detachView(retainInstance: Boolean)
}