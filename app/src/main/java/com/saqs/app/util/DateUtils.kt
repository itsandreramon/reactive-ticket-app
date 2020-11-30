/*
 * Copyright 2020 - André Thiele
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.util

import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeParseException

object DateUtils {

    fun nowUtc(): String {
        return ZonedDateTime
            .ofInstant(Instant.now(), ZoneId.of("UTC"))
            .toString()
    }

    @Throws(DateTimeParseException::class)
    fun fromUtcString(
        utc: String
    ): ZonedDateTime {
        val instantUtc = Instant.parse(utc)
        return ZonedDateTime
            .ofInstant(instantUtc, ZoneId.of("UTC"))
    }

    fun toUtcString(
        utc: ZonedDateTime
    ): String {
        return utc.toString()
    }

    fun toLocalString(
        utc: ZonedDateTime
    ): String {
        return utc.toLocalDateTime().toString()
    }
}
