/*
 * Copyright 2020 - André Thiele
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.util

import com.google.firebase.Timestamp
import com.saqs.app.util.DateUtils.fromTimestamp
import com.saqs.app.util.DateUtils.fromUtcString
import com.saqs.app.util.DateUtils.toUtcString
import io.kotest.matchers.shouldBe
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Date
import org.junit.Test

class DateUtilsTest {

    @Test
    fun toLocalFormattedDateTest() {
        // Given
        val zoneId = ZoneId.of("Europe/Berlin")
        val expected = ZonedDateTime.of(2020, 12, 6, 13, 0, 0, 0, zoneId)
        val instant = expected.toInstant()

        // When
        val actual = DateUtils.toLocalFormattedDate(instant, zoneId)

        // Then
        actual shouldBe expected.format(DateTimeFormatter.ofPattern("EEE, d MMM yyyy"))
    }

    @Test
    fun toUtcStringTest() {
        // Given
        val zoneId = ZoneId.of("Europe/Berlin")
        val date = ZonedDateTime.of(2020, 12, 6, 13, 0, 0, 0, zoneId)

        val expected = DateTimeFormatter
                .ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
                .withZone(ZoneOffset.UTC)
                .format(date)

        // When
        val actual = toUtcString(date.toInstant())

        // Then
        actual shouldBe expected
    }

    @Test
    fun fromUtcStringTest() {
        // Given
        val zoneId = ZoneId.of("Europe/Berlin")
        val expected = ZonedDateTime.of(2020, 12, 6, 13, 0, 0, 0, zoneId)

        val expectedAsString = DateTimeFormatter
                .ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
                .withZone(ZoneOffset.UTC)
                .format(expected)

        // When
        val actual = fromUtcString(expectedAsString)

        // Then
        actual shouldBe expected.toInstant()
    }

    @Test
    fun fromTimestampTest() {
        // Given
        val timestamp = Timestamp(Date(2020, 12, 6, 14, 13, 50))
        val expected = timestamp.toDate().toInstant()

        // When
        val actual = fromTimestamp(timestamp)

        // Then
        actual shouldBe expected
    }
}
