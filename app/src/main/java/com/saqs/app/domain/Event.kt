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
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.firebase.firestore.FirebaseFirestore
import com.saqs.app.data.database.Converters
import com.saqs.app.util.round
import java.time.ZoneId
import java.time.ZonedDateTime
import kotlinx.parcelize.Parcelize

@Parcelize
@TypeConverters(Converters::class)
@Entity(tableName = "events")
class Event(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val name: String = "",

    val date: ZonedDateTime = ZonedDateTime.now(ZoneId.of("UTC")),

    val availableTickets: Int = 0,

    val availableTicketsOverall: Int = 0
) : Parcelable {

    val availableTicketsPercentage: Double
        get() = availableTickets.toDouble()
            .div(availableTicketsOverall.toDouble())
            .round(2)

    fun updateSelf() {
        val db = FirebaseFirestore.getInstance()
    }
}

class EventDiffCallback : DiffUtil.ItemCallback<Event>() {
    override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
        // TODO
        return false
    }
}
