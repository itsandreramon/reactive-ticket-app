/*
 * Copyright 2020 - André Thiele
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
import com.saqs.app.databinding.ViewHolderEventItemGreenBinding
import com.saqs.app.databinding.ViewHolderEventItemRedBinding
import com.saqs.app.databinding.ViewHolderEventItemYellowBinding
import com.saqs.app.domain.Event
import com.saqs.app.domain.EventDiffCallback

class EventItemAdapter(
    private val listener: EventItemClickListener,
    private val context: Context
) : ListAdapter<Event, RecyclerView.ViewHolder>(EventDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            TYPE_GREEN -> {
                val binding = ViewHolderEventItemGreenBinding.inflate(layoutInflater, parent, false)
                EventItemViewHolderGreen(
                    parent.context,
                    binding
                )
            }
            TYPE_YELLOW -> {
                val binding = ViewHolderEventItemYellowBinding.inflate(layoutInflater, parent, false)
                EventItemViewHolderYellow(
                    parent.context,
                    binding
                )
            }
            TYPE_RED -> {
                val binding = ViewHolderEventItemRedBinding.inflate(layoutInflater, parent, false)
                EventItemViewHolderRed(
                    parent.context,
                    binding
                )
            }
            else -> {
                throw IllegalStateException("Invalid view type: $viewType")
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            TYPE_GREEN -> {
                (holder as EventItemViewHolderGreen).bind(getItem(position), listener)
            }
            TYPE_YELLOW -> {
                (holder as EventItemViewHolderYellow).bind(getItem(position), listener)
            }
            TYPE_RED -> {
                (holder as EventItemViewHolderRed).bind(getItem(position), listener)
            }
            else -> {
                throw IllegalStateException("Invalid view type: ${getItemViewType(position)}")
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val availabilityInPercent = getItem(position).availableTicketsPercentage

        if (availabilityInPercent in 0.5F..1.0F) {
            return TYPE_GREEN
        }

        if (availabilityInPercent in 0.25F..0.5F) {
            return TYPE_YELLOW
        }

        if (availabilityInPercent < 0.25F) {
            return TYPE_RED
        }

        return -1
    }

    interface EventItemClickListener {
        fun onEventItemClicked(eventItem: Event)
    }

    companion object {
        const val TYPE_GREEN = 1
        const val TYPE_YELLOW = 2
        const val TYPE_RED = 3
    }
}
