package com.projekt.zespolowy.fun_organizer.register

/**
 * Created by viktor on 22.03.2018.
 */

enum class CheckCorrectnessRet(val err : Int) {
    E_MAIL_NOT_GIVEN(0),
    PASSWORDS_NOT_MATCH(1),
    PASSWORD_TOO_SHORT(2),
    DATA_CORRECT(3)
}