/*
 * Copyright 2020 - André Thiele
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.domain

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.firebase.Timestamp
import com.saqs.app.db.Converters
import com.saqs.app.util.round
import kotlinx.parcelize.Parcelize

@Entity(
    tableName = "events",
    indices = [Index(value = ["id"])]
)
@TypeConverters(Converters::class)
@Parcelize
data class Event(

    @PrimaryKey
    var id: String = "-1",

    val name: String = "",

    val date: Timestamp = Timestamp.now(),

    val available: Int = 0,

    val amount: Int = 0,

    val image: String? = null
) : Parcelable {

    val availableTicketsPercentage: Double
        get() = available.toDouble()
            .div(amount.toDouble())
            .round(2)
}

class EventDiffCallback : DiffUtil.ItemCallback<Event>() {
    override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
        return (oldItem.image == newItem.image) &&
                (oldItem.name == newItem.image) &&
                (oldItem.date == newItem.date)
    }
}
