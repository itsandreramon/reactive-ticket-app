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
import com.saqs.app.data.database.Converters
import kotlinx.parcelize.Parcelize

@Parcelize
@TypeConverters(Converters::class)
@Entity(tableName = "tickets")
class Ticket(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val owner: String,

    val eventId: Int
) : Parcelable

class TicketDiffCallback : DiffUtil.ItemCallback<Ticket>() {
    override fun areItemsTheSame(oldItem: Ticket, newItem: Ticket): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Ticket, newItem: Ticket): Boolean {
        // TODO
        return false
    }
}
