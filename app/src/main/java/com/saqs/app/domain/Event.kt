/*
 * Copyright 2020 - André Thiele
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.domain

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import com.google.firebase.Timestamp
import com.saqs.app.util.round
import kotlinx.parcelize.Parcelize

@Parcelize
class Event(
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
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
        return (oldItem.name == newItem.name) &&
                (oldItem.date == newItem.date)
    }
}
