/*
 * Copyright 2020 - André Thiele
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.domain

import androidx.recyclerview.widget.DiffUtil

data class TicketWithEvent(
    val ticket: Ticket,
    val event: Event,
    val amount: Int
)

class TicketWithEventDiffCallback : DiffUtil.ItemCallback<TicketWithEvent>() {
    override fun areItemsTheSame(oldItem: TicketWithEvent, newItem: TicketWithEvent): Boolean {
        return false
    }

    override fun areContentsTheSame(oldItem: TicketWithEvent, newItem: TicketWithEvent): Boolean {
        return false
    }
}
