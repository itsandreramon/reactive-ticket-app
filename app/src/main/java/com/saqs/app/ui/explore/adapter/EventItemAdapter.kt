/*
 * Copyright 2020 - André Thiele, Allan Fodi, Hüseyin Celik, Bertin Junior Wagueu Nkepgang
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.ui.explore.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.saqs.app.databinding.ViewHolderEventItemBinding
import com.saqs.app.domain.Event
import com.saqs.app.domain.EventDiffCallback

class EventItemAdapter(
    private val listener: EventItemClickListener,
    private val context: Context
) : ListAdapter<Event, RecyclerView.ViewHolder>(EventDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ViewHolderEventItemBinding.inflate(layoutInflater, parent, false)

        return EventItemViewHolder(
            parent.context,
            binding
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as EventItemViewHolder).bind(getItem(position), listener)
    }

    interface EventItemClickListener {
        fun onEventItemClicked(eventItem: Event)
    }
}
