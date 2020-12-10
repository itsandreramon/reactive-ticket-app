/*
 * Copyright 2020 - André Thiele
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.util

import com.saqs.app.domain.AvailabilityColor

interface EventAvailabilityHighlighter {
    fun computeAvailabilityColor(availabilityInPercent: Double): AvailabilityColor
}
