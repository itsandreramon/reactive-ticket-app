package com.saqs.app.domain

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import com.saqs.app.util.round
import kotlinx.parcelize.Parcelize
import java.time.ZonedDateTime
import kotlin.math.round

@Parcelize
class Event(
    val id: Int = 0,

    val name: String = "",

    val date: ZonedDateTime = ZonedDateTime.now(),

    val availableTickets: Int = 0,

    val availableTicketsOverall: Int = 0
) : Parcelable {

    val availableTicketsPercentage: Double
        get() = availableTickets.toDouble()
            .div(availableTicketsOverall.toDouble())
            .round(2)
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