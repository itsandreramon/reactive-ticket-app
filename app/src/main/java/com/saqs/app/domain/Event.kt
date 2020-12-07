/*
 * Copyright 2020 - André Thiele, Allan Fodi, Hüseyin Celik, Bertin Junior Wagueu Nkepgang
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.domain

import androidx.annotation.ColorRes
import androidx.recyclerview.widget.DiffUtil
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.firebase.Timestamp
import com.saqs.app.R
import com.saqs.app.db.Converters
import com.saqs.app.util.round

@Entity(
    tableName = "events",
    indices = [Index(value = ["id"])]
)
@TypeConverters(Converters::class)
data class Event(

    @PrimaryKey
    var id: String = "-1",

    val name: String = "",

    val date: Timestamp = Timestamp.now(),

    val available: Int = 0,

    val amount: Int = 0,

    val image: String? = null
) {
    val availableTicketsPercentage: Double
        get() = available.toDouble()
            .div(amount.toDouble())
            .round(2)

    val availableTicketsColor: AvailableTicketsColor
        get() = when (availableTicketsPercentage) {
            in 0.1..1.0 -> {
                AvailableTicketsColor.Green
            }
            in 0.05..0.1 -> {
                AvailableTicketsColor.Yellow
            }
            else -> {
                AvailableTicketsColor.Red
            }
        }
}

sealed class AvailableTicketsColor(@ColorRes val color: Int) {
    object Green : AvailableTicketsColor(R.color.green)
    object Yellow : AvailableTicketsColor(R.color.yellow)
    object Red : AvailableTicketsColor(R.color.red)
}

class EventDiffCallback : DiffUtil.ItemCallback<Event>() {
    override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
        return (oldItem.image == newItem.image) &&
                (oldItem.name == newItem.image) &&
                (oldItem.date == newItem.date)
    }
}
