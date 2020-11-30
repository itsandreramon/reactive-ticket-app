package com.saqs.app.ui.home.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.saqs.app.databinding.ViewHolderEventItemGreenBinding
import com.saqs.app.domain.Event

class EventItemViewHolderGreen(
    private val context: Context,
    private val binding: ViewHolderEventItemGreenBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(eventItem: Event, listener: EventItemAdapter.EventItemClickListener) {
        binding.root.setOnClickListener {
            listener.onEventItemClicked(eventItem)
        }
    }
}