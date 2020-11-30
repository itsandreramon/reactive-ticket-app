package com.saqs.app.ui.home.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.saqs.app.databinding.ViewHolderEventItemRedBinding
import com.saqs.app.domain.Event

class EventItemViewHolderRed(
    private val context: Context,
    private val binding: ViewHolderEventItemRedBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(eventItem: Event, listener: EventItemAdapter.EventItemClickListener) {
        binding.root.setOnClickListener {
            listener.onEventItemClicked(eventItem)
        }
    }
}