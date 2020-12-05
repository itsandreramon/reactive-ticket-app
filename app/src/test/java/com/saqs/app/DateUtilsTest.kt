package com.saqs.app

import org.junit.Assert
import org.junit.Test
import java.time.format.DateTimeFormatter


class DateUtilsTest {
    @Test
    fun testToLocalFormattedDate(date: String) {
        val day = testToLocalFormattedDate(date.format(DateTimeFormatter.ofPattern("Mon, 14 May 2018 11:47:11 GMT")));
        Assert.assertEquals("Mon, 14 May 2018 11:47:11 GMT", day)
    }
}