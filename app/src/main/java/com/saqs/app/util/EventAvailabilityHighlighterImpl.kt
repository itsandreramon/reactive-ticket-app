/*
 * Copyright 2020 - André Thiele
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.util

import com.saqs.app.domain.AvailabilityColor

class EventAvailabilityHighlighterImpl : EventAvailabilityHighlighter {
    override fun computeAvailabilityColor(availabilityInPercent: Double): AvailabilityColor {
        return when (availabilityInPercent) {
            in 10.0..100.0 -> {
                AvailabilityColor.Green
            }
            in 5.0..10.0 -> {
                AvailabilityColor.Yellow
            }
            in 0.0..5.0 -> {
                AvailabilityColor.Red
            }
            else -> {
                AvailabilityColor.Unknown
            }
        }
    }
}
