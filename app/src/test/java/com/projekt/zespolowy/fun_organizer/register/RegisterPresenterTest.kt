package com.projekt.zespolowy.fun_organizer.register

import com.nhaarman.mockito_kotlin.*
import com.projekt.zespolowy.fun_organizer.main.MainPresenter
import com.projekt.zespolowy.fun_organizer.main.MainView
import com.projekt.zespolowy.fun_organizer.main.PingUseCase
import com.projekt.zespolowy.fun_organizer.ping.PingResponseObject
import com.projekt.zespolowy.fun_organizer.utils.SchedulersProvider
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test

/**
 * Created by viktor on 22.03.2018.
 */

class RegisterPresenterTest {

    private lateinit var mockView: RegisterView
    private lateinit var mockRegisterUseCase: RegisterUseCase
    private lateinit var mockSchedulersProvider: SchedulersProvider
    private lateinit var presenter: RegisterPresenter

    private val responseOnSuccess = UserModel("email", "password", "name", "surname", "1234")
    private val throwable = Throwable()
    private lateinit var user: UserModel
    @Before
    fun setUp() {
        mockView = mock()
        mockRegisterUseCase = mock()
        mockSchedulersProvider = mock()
        presenter = RegisterPresenter(mockRegisterUseCase, mockSchedulersProvider)
        presenter.onStart(mockView)

        whenever(mockSchedulersProvider.backgroundThread()).thenReturn(Schedulers.trampoline())
        whenever(mockSchedulersProvider.mainThread()).thenReturn(Schedulers.trampoline())
    }

    @Test
    fun shouldCreateNewUser() {
        whenever(mockRegisterUseCase.postUserToDatabase(any())).thenReturn(Single.just(responseOnSuccess))
        presenter.postUserToDatabase(responseOnSuccess, "password")
        verify(mockView).toast(eq(responseOnSuccess.toString()))
    }

    @Test
    fun shouldNotAllowRegistrationWithoutEmail() {
        user = UserModel("", "password", "", "", "" )
        presenter.postUserToDatabase(user, "password")
        verify(mockView).toast(eq("email not given"))
    }

    @Test
    fun shouldNotAllowPasswordsNotMatching() {
        user = UserModel("ccvwq", "password", "", "", "" )
        presenter.postUserToDatabase(user, "paswordddd222")
        verify(mockView).toast(eq("passwords not match"))
    }

    @Test
    fun shouldNotAllowTooShortPassword() {
        user = UserModel("ccvwq", "ip", "", "", "" )
        presenter.postUserToDatabase(user, "ip")
        verify(mockView).toast(eq("password too short"))
    }
}

