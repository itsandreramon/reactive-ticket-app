/*
 * Copyright 2020 - André Thiele, Allan Fodi, Hüseyin Celik, Bertin Junior Wagueu Nkepgang
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.ui.wallet.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.saqs.app.databinding.ViewHolderTicketItemBinding
import com.saqs.app.domain.Ticket
import com.saqs.app.domain.TicketWithEvent
import com.saqs.app.domain.TicketWithEventDiffCallback

class TicketItemAdapter(
    private val listener: TicketItemClickListener,
    private val context: Context
) : ListAdapter<TicketWithEvent, RecyclerView.ViewHolder>(TicketWithEventDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ViewHolderTicketItemBinding.inflate(layoutInflater, parent, false)

        return TicketItemViewHolder(
            parent.context,
            binding
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as TicketItemViewHolder).bind(getItem(position), listener)
    }

    interface TicketItemClickListener {
        fun onTicketItemClicked(ticketItem: Ticket)
    }
}
