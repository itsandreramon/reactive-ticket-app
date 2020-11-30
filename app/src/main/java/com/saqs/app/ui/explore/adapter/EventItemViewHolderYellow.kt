/*
 * Copyright 2020 - André Thiele
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.ui.explore.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.saqs.app.databinding.ViewHolderEventItemYellowBinding
import com.saqs.app.domain.Event

class EventItemViewHolderYellow(
    private val context: Context,
    private val binding: ViewHolderEventItemYellowBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(eventItem: Event, listener: EventItemAdapter.EventItemClickListener) {
        binding.root.setOnClickListener {
            listener.onEventItemClicked(eventItem)
        }
    }
}
