package com.projekt.zespolowy.fun_organizer.register

import com.projekt.zespolowy.fun_organizer.base.BasePresenter
import com.projekt.zespolowy.fun_organizer.utils.SchedulersProvider

/**
 * Created by viktor on 21.03.2018.
 */

class RegisterPresenter(
        private val registerUseCase: RegisterUseCase,
        private val schedulersProvider: SchedulersProvider
) : BasePresenter<RegisterView>() {

    private lateinit var ret: CheckCorrectnessRet

    fun onStart(registerView: RegisterView) {
        attachView(registerView)
    }

    fun onStop() {
        detachView(false)
    }


    fun checkCorrectness(user: UserModel, secPassword: String): CheckCorrectnessRet {
        if (user.email.trim().length <= 0)
            return CheckCorrectnessRet.E_MAIL_NOT_GIVEN
        if (user.password != secPassword)
            return CheckCorrectnessRet.PASSWORDS_NOT_MATCH
        if (user.password.length < 5)
            return CheckCorrectnessRet.PASSWORD_TOO_SHORT
        return CheckCorrectnessRet.DATA_CORRECT
    }

    fun postUserToDatabase(user: UserModel, secPassword: String): Boolean {
        ret = checkCorrectness(user, secPassword)
        if (ret.equals(CheckCorrectnessRet.DATA_CORRECT)) {
            registerUseCase
                    .postUserToDatabase(user)
                    .subscribeOn(schedulersProvider.backgroundThread())
                    .observeOn(schedulersProvider.mainThread())
                    .subscribe(
                            {
                                view?.toast(it.toString())
                            },
                            {
                                view?.toast(it.toString())
                            }
                    )
            //return to login screen or autologin
            return true
        } else if (ret.equals(CheckCorrectnessRet.E_MAIL_NOT_GIVEN))
            view?.toast("email not given")
        else if (ret.equals(CheckCorrectnessRet.PASSWORDS_NOT_MATCH))
            view?.toast("passwords not match")
        else if (ret.equals(CheckCorrectnessRet.PASSWORD_TOO_SHORT))
            view?.toast("password too short")
        return false
    }


}