package com.saqs.app

import com.saqs.app.util.DateUtils.toLocalFormattedDate
import com.saqs.app.util.DateUtils.toUtcString
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.time.*
import java.time.format.DateTimeFormatter


class DateUtilsTest : FunSpec({
    test("toLocalFormattedDateTest") {
        val zoneId = ZoneId.of("Europe/Berlin")
        val expected = ZonedDateTime.of(2020, 12, 6, 13, 0, 0, 0, zoneId)
        val instant = expected.toInstant()

        val actual = toLocalFormattedDate(instant, zoneId)
        System.out.println("Test instant " + Instant.now())
        actual shouldBe expected.format(DateTimeFormatter.ofPattern("EEE, d MMM yyyy"))
    }

    test("toUtcStringTest") {
        val expected = DateTimeFormatter
                .ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
                .withZone(ZoneOffset.UTC)
                .format(Instant.now())

        val actual = toUtcString(Instant.now())
        actual shouldBe expected
    }

})
