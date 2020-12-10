/*
 * Copyright 2020 - André Thiele
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.ui.explore.adapter

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.saqs.app.databinding.ViewHolderEventItemBinding
import com.saqs.app.domain.Event
import com.saqs.app.extensions.loadImageElsePlaceholder
import com.saqs.app.util.DateUtils
import com.saqs.app.util.EventAvailabilityHighlighterImpl

class EventItemViewHolder(
    private val context: Context,
    private val binding: ViewHolderEventItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(eventItem: Event, listener: EventItemAdapter.EventItemClickListener) {
        binding.root.setOnClickListener {
            listener.onEventItemClicked(eventItem)
        }

        binding.ivImage.loadImageElsePlaceholder(eventItem.image)
        binding.tvTitle.text = eventItem.name
        binding.tvDate.text = DateUtils.toLocalFormattedDate(DateUtils.fromTimestamp(eventItem.date))

        binding.tvAvailable.apply {
            text = "${eventItem.available} left"
            setTextColor(determineColorByPercentage(eventItem.availableTicketsPercentage))
        }
    }

    private fun determineColorByPercentage(percentage: Double): Int {
        return ContextCompat.getColor(
            context,
            EventAvailabilityHighlighterImpl().computeAvailabilityColor(percentage).color
        )
    }
}
