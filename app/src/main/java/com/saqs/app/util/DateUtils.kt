/*
 * Copyright 2020 - André Thiele, Allan Fodi, Hüseyin Celik, Bertin Junior Wagueu Nkepgang
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.util

import com.google.firebase.Timestamp
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

object DateUtils {

    fun nowUtc(): String {
        return Instant.now().toString()
    }

    fun toUtcString(
        utc: Instant
    ): String {
        return utc.toString()
    }

    fun toLocalFormattedDate(
        utc: Instant,
        zonedId: ZoneId = ZoneId.systemDefault()
    ): String {
        val zonedDateTime = ZonedDateTime.ofInstant(utc, zonedId)
        return zonedDateTime.format(DateTimeFormatter.ofPattern("EEE, d MMM yyyy"))
    }

    fun fromUtcString(
        utc: String
    ): Instant {
        return Instant.parse(utc)
    }

    fun fromTimestamp(
        timestampUtc: Timestamp
    ): Instant {
        return timestampUtc.toDate().toInstant()
    }
}
