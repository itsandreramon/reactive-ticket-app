/*
 * Copyright 2020 - André Thiele
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.data.database

import androidx.room.TypeConverter
import com.saqs.app.util.DateUtils
import java.time.ZonedDateTime

class Converters {

    @TypeConverter
    fun fromString(listAsString: String): List<String> {
        return listAsString.split(",").map { it }
    }

    @TypeConverter
    fun toString(list: List<String>): String {
        return list.joinToString(separator = ",")
    }

    @TypeConverter
    fun dateFromString(utc: String): ZonedDateTime {
        return DateUtils.fromUtcString(utc)
    }

    @TypeConverter
    fun dateToString(date: ZonedDateTime): String {
        return DateUtils.toUtcString(date)
    }
}
