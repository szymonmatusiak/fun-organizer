package com.projekt.zespolowy.fun_organizer.main

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import com.projekt.zespolowy.fun_organizer.api.PingResponseObject
import com.projekt.zespolowy.fun_organizer.utils.ApiProvider
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

/**
 * Created by szymon on 18.03.2018.
 */
@Suppress("IllegalIdentifier")
class PingUseCaseTest {
    private lateinit var mockApiProvider: ApiProvider
    private lateinit var pingUseCase: PingUseCase

    private val responseOnSuccess = PingResponseObject("test", 1)

    @Before
    fun setUp() {
        mockApiProvider = mock()
        pingUseCase = PingUseCase(mockApiProvider)
    }

    @Test
    fun `post to database should retun responseObject`() {
        whenever(mockApiProvider.postToDatabase(responseOnSuccess)).thenReturn(Single.just(responseOnSuccess))
        val postToDatabase = pingUseCase.postToDatabase(responseOnSuccess)
        val test = postToDatabase.test()
        test.assertValue(responseOnSuccess)
    }

    @Test
    fun `post to database should retun error`() {
        val throwable = Throwable()
        whenever(mockApiProvider.postToDatabase(responseOnSuccess)).thenReturn(Single.error(throwable))
        val postToDatabase = pingUseCase.postToDatabase(responseOnSuccess)
        val test = postToDatabase.test()
        test.assertError(Throwable::class.java)
    }
}