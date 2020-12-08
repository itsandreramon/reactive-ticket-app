package com.saqs.app

import com.saqs.app.util.DateUtils.fromTimestamp
import com.saqs.app.util.DateUtils.fromUtcString
import com.saqs.app.util.DateUtils.toLocalFormattedDate
import com.saqs.app.util.DateUtils.toUtcString
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.time.Instant
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Date


class DateUtilsTest : FunSpec({
    test("toLocalFormattedDateTest") {
        val zoneId = ZoneId.of("Europe/Berlin")
        val expected = ZonedDateTime.of(2020, 12, 6, 13, 0, 0, 0, zoneId)
        val instant = expected.toInstant()

        val actual = toLocalFormattedDate(instant, zoneId)
        println("Test instant " + Instant.now())
        actual shouldBe expected.format(DateTimeFormatter.ofPattern("EEE, d MMM yyyy"))
    }

    test("toUtcStringTest") {
        val zoneId = ZoneId.of("Europe/Berlin")
        val date = ZonedDateTime.of(2020,12,6,13,0,0,0,zoneId)

        val expected = DateTimeFormatter
                .ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
                .withZone(ZoneOffset.UTC)
                .format(date)

        val actual = toUtcString(date.toInstant())
        actual shouldBe expected
    }

    test("fromUtcStringTest"){
        val zoneId = ZoneId.of("Europe/Berlin")
        val expected = ZonedDateTime.of(2020,12,6,13,0,0,0,zoneId)

        val expectedAsString = DateTimeFormatter
                .ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
                .withZone(ZoneOffset.UTC)
                .format(expected)

        val actual = fromUtcString(expectedAsString)
        actual shouldBe expected.toInstant()
    }

    test("fromTimestampTest"){
        val timestamp = com.google.firebase.Timestamp(Date(2020,12,6,14,13,50))
        val actual = fromTimestamp(timestamp)
        val expected = timestamp.toDate().toInstant()
        actual shouldBe expected
    }

})
