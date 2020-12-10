/*
 * Copyright 2020 - André Thiele, Allan Fodi, Hüseyin Celik, Bertin Junior Wagueu Nkepgang
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.ui.wallet.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.saqs.app.databinding.ViewHolderTicketItemBinding
import com.saqs.app.domain.TicketWithEvent
import com.saqs.app.extensions.loadImageElsePlaceholder
import com.saqs.app.util.DateUtils

class TicketItemViewHolder(
    private val context: Context,
    private val binding: ViewHolderTicketItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(ticketWithEvent: TicketWithEvent, listener: TicketItemAdapter.TicketItemClickListener) {
        binding.root.setOnClickListener {
            listener.onTicketItemClicked(ticketWithEvent.ticket)
        }

        binding.tvTitle.text = "${ticketWithEvent.event.name}"
        binding.tvDate.text = "${ticketWithEvent.amount} Tickets (${DateUtils.toLocalFormattedDate(DateUtils.fromTimestamp(ticketWithEvent.event.date))})"
        binding.ivImage.loadImageElsePlaceholder(ticketWithEvent.event.image)
    }
}
