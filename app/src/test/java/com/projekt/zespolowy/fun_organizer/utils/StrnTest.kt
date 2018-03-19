package com.projekt.zespolowy.fun_organizer.utils

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Created by szymon on 18.03.2018.
 */
class StrnTest {
    private var strn = Strn()

    @Test
    fun parseStringContainingASD() {
        val string = "rwaraSasd"
        val parseString = strn.parseString(string)
        assertEquals(parseString, "asd")
    }

    @Test
    fun parseCorrectString() {
        val string = "rwaraSsd"
        val parseString = strn.parseString(string)
        assertEquals(parseString, string)
    }

    @Test
    fun parsBlankString() {
        val string = " "
        val parseString = strn.parseString(string)
        assertEquals(parseString, "")
    }

    @Test
    fun parseEmptytring() {
        val string = ""
        val parseString = strn.parseString(string)
        assertEquals(parseString, "")
    }
}