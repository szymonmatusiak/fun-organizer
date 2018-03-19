package com.projekt.zespolowy.fun_organizer.main

import com.nhaarman.mockito_kotlin.*
import com.projekt.zespolowy.fun_organizer.ping.PingResponseObject
import com.projekt.zespolowy.fun_organizer.utils.SchedulersProvider
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test

/**
 * Created by szymon on 18.03.2018.
 */
class MainPresenterTest {

    private lateinit var mockView: MainView
    private lateinit var mockPingUseCase: PingUseCase
    private lateinit var mockSchedulersProvider: SchedulersProvider
    private lateinit var presenter: MainPresenter

    private val responseOnSuccess = PingResponseObject("test", 1)
    private val throwable = Throwable()
    @Before
    fun setUp() {
        mockView = mock()
        mockPingUseCase = mock()
        mockSchedulersProvider = mock()
        presenter = MainPresenter(mockPingUseCase, mockSchedulersProvider)
        presenter.onStart(mockView)

        whenever(mockSchedulersProvider.backgroundThread()).thenReturn(Schedulers.trampoline())
        whenever(mockSchedulersProvider.mainThread()).thenReturn(Schedulers.trampoline())
    }

    @Test
    fun shouldReturnResultAfterSendingObjectToApi() {
        whenever(mockPingUseCase.postToDatabase(any())).thenReturn(Single.just(responseOnSuccess))
        presenter.postToDatabase(responseOnSuccess)
        verify(mockView).toast(eq(responseOnSuccess.toString()))
    }

    @Test
    fun shouldReturnErrorAfterSendingObjectToApi() {
        whenever(mockPingUseCase.postToDatabase(any())).thenReturn(Single.error(throwable))
        presenter.postToDatabase(responseOnSuccess)
        verify(mockView).toast(eq(throwable.toString()))
    }

    @Test
    fun shouldReturnPingAfterSendingPingRequest() {
        whenever(mockPingUseCase.getPing()).thenReturn(Single.just(responseOnSuccess))
        presenter.getPingResponse()
        verify(mockView).toast(eq(responseOnSuccess.toString()))
    }

    @Test
    fun shouldReturnErrorAfterSendingPingRequest() {
        whenever(mockPingUseCase.getPing()).thenReturn(Single.error(throwable))
        presenter.getPingResponse()
        verify(mockView).toast(eq(throwable.toString()))
    }
}