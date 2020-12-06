package com.saqs.app

import com.saqs.app.util.DateUtils.toLocalFormattedDate
import org.junit.Assert
import org.junit.Test
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class DateUtilsTest {
    @Test
    fun testToLocalFormattedDate(date: String) {
        val dtf = DateTimeFormatter.ofPattern("EEE, d MMM yyyy - HH:mm")
        val now: LocalDateTime = LocalDateTime.now()
        val result = dtf.format(now)
        println(dtf.format(now))


        //var formatter = DateTimeFormatter.ofPattern("dd-MMMM-yyyy")
        //var formattedDate = date.format(formatter)

        val date : String = date.format(DateTimeFormatter.ofPattern("Mon, 14 May 2018 11:47"))
        val day = toLocalFormattedDate(date)
        Assert.assertEquals(result, day)
    }
}
