package com.saqs.app

import com.saqs.app.util.DateUtils
import com.saqs.app.util.DateUtils.toLocalFormattedDate
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import org.junit.Assert
import org.junit.Test
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter


class DateUtilsTest : FunSpec( {
    test("toLocalFormattedDateTest") {
        val zoneId = ZoneId.of("Europe/Berlin")
        val expected = ZonedDateTime.of(2020, 12, 6, 13, 0, 0, 0, zoneId)
        val instant = expected.toInstant()

        val actual = toLocalFormattedDate(instant, zoneId)

        actual shouldBe expected.format(DateTimeFormatter.ofPattern("EEE, d MMM yyyy"))
    }
})
