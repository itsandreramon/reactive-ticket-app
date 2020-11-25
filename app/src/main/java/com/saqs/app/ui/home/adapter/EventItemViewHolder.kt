package com.saqs.app.ui.home.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.saqs.app.databinding.ViewHolderEventItemBinding
import com.saqs.app.domain.Event
import com.saqs.app.ui.home.adapter.EventItemAdapter.Companion.TYPE_GREEN
import com.saqs.app.ui.home.adapter.EventItemAdapter.Companion.TYPE_RED
import com.saqs.app.ui.home.adapter.EventItemAdapter.Companion.TYPE_YELLOW

class EventItemViewHolder(
    private val context: Context,
    private val binding: ViewHolderEventItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(eventItem: Event, viewType: Int, listener: EventItemAdapter.EventItemClickListener) {
        binding.root.setOnClickListener {
            listener.onEventItemClicked(eventItem)
        }

        when (viewType) {
            TYPE_GREEN -> {
                binding.textViewTitle.text = "Green"
            }
            TYPE_YELLOW -> {
                binding.textViewTitle.text = "Yellow"
            }
            TYPE_RED -> {
                binding.textViewTitle.text = "Red"
            }
        }
    }
}