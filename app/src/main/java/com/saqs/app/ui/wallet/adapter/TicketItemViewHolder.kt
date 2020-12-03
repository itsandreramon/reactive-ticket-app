/*
 * Copyright 2020 - André Thiele
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.ui.wallet.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.saqs.app.databinding.ViewHolderTicketItemBinding
import com.saqs.app.domain.Ticket

class TicketItemViewHolder(
    private val context: Context,
    private val binding: ViewHolderTicketItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(ticketItem: Ticket, listener: TicketItemAdapter.TicketItemClickListener) {
        binding.root.setOnClickListener {
            listener.onTicketItemClicked(ticketItem)
        }
    }
}
