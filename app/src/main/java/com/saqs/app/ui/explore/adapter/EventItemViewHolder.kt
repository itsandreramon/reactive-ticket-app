/*
 * Copyright 2020 - André Thiele
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.ui.explore.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.saqs.app.databinding.ViewHolderEventItemBinding
import com.saqs.app.domain.Event
import com.saqs.app.extensions.loadImageElsePlaceholder
import com.saqs.app.util.DateUtils

class EventItemViewHolder(
    private val context: Context,
    private val binding: ViewHolderEventItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(eventItem: Event, listener: EventItemAdapter.EventItemClickListener) {
        binding.btnBuy.setOnClickListener {
            listener.onEventItemClicked(eventItem)
        }

        binding.ivImage.loadImageElsePlaceholder()

        binding.tvTitle.text = eventItem.id.toString()
        binding.tvLocation.text = DateUtils.toLocalString(eventItem.date)

        val availabilityInPercent = eventItem.availableTicketsPercentage
    }
}
