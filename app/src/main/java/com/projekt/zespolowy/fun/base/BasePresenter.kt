package com.projekt.zespolowy.`fun`.base

import java.lang.ref.WeakReference

/**
 * Created by szymon on 10.03.18.
 */
abstract class BasePresenter<V : IBaseView> : IBasePresenter<V> {

    private var viewReference: WeakReference<V>? = null

    override fun attachView(view: V) {
        viewReference = WeakReference(view)
    }

    override fun detachView(retainInstance: Boolean) {
        viewReference?.clear()
        viewReference = null
    }

    protected val view: V?
        get() = viewReference?.get()

    protected val isViewAttached: Boolean
        get() = viewReference?.get() != null
}