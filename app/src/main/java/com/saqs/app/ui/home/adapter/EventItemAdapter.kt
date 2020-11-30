package com.saqs.app.ui.home.adapter

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

        return when (viewType) {
            TYPE_GREEN -> {
                val binding = ViewHolderEventItemBinding.inflate(layoutInflater, parent, false)
                EventItemViewHolder(
                    parent.context,
                    binding
                )
            }
            TYPE_YELLOW -> {
                val binding = ViewHolderEventItemBinding.inflate(layoutInflater, parent, false)
                EventItemViewHolder(
                    parent.context,
                    binding
                )
            }
            TYPE_RED -> {
                val binding = ViewHolderEventItemBinding.inflate(layoutInflater, parent, false)
                EventItemViewHolder(
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
                (holder as EventItemViewHolder).bind(getItem(position), TYPE_GREEN, listener)
            }
            TYPE_YELLOW -> {
                (holder as EventItemViewHolder).bind(getItem(position), TYPE_YELLOW, listener)
            }
            TYPE_RED -> {
                (holder as EventItemViewHolder).bind(getItem(position), TYPE_RED, listener)
            }
            else -> {
                throw IllegalStateException("Invalid view type: ${getItemViewType(position)}")
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        // val availabilityInPercent = getItem(position).availableTicketsInPercent
        val availabilityInPercent = 0.5F

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