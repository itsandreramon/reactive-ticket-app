package com.saqs.app.db

import androidx.room.TypeConverter
import com.google.firebase.Timestamp
import com.saqs.app.util.DateUtils
import java.util.Date

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
    fun dateFromTimestamp(timestamp: Timestamp): String {
        return DateUtils.fromTimestamp(timestamp).toString()
    }

    @TypeConverter
    fun dateToTimestamp(date: String): Timestamp {
        return Timestamp(Date.from(DateUtils.fromUtcString(date).toInstant()))
    }
}