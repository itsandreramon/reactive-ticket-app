/*
 * Copyright 2020 - André Thiele, Allan Fodi, Hüseyin Celik, Bertin Junior Wagueu Nkepgang
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.util

import com.saqs.app.domain.AvailabilityColor
import com.saqs.app.domain.Event
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class EventAvailabilityHighlighterImplTest {

    @Test
    fun correctlyDeterminesGreenAvailability() {
        // Given
        val expected = AvailabilityColor.Green

        // When
        val actual = Event(amount = 100, available = 11).availabilityColor

        // Then
        actual shouldBe expected
    }

    @Test
    fun correctlyDeterminesYellowAvailability() {
        // Given
        val expected = AvailabilityColor.Yellow

        // When
        val actual = Event(amount = 100, available = 7).availabilityColor

        // Then
        actual shouldBe expected
    }

    @Test
    fun correctlyDeterminesRedAvailability() {
        // Given
        val expected = AvailabilityColor.Red

        // When
        val actual = Event(amount = 100, available = 1).availabilityColor

        // Then
        actual shouldBe expected
    }

    @Test
    fun correctlyDeterminesUnknownAvailabilityWhenLessThanZero() {
        // Given
        val expected = AvailabilityColor.Unknown

        // When
        val actual = Event(amount = 100, available = -1).availabilityColor

        // Then
        actual shouldBe expected
    }

    @Test
    fun correctlyDeterminesUnknownAvailabilityWhenMoreThanHundred() {
        // Given
        val expected = AvailabilityColor.Unknown

        // When
        val actual = Event(amount = 100, available = 101).availabilityColor

        // Then
        actual shouldBe expected
    }
}
