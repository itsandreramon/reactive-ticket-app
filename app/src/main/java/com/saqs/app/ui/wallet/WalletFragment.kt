/*
 * Copyright 2020 - André Thiele
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.ui.wallet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.saqs.app.databinding.FragmentWalletBinding
import com.saqs.app.domain.Ticket
import com.saqs.app.ui.wallet.adapter.TicketItemAdapter
import com.saqs.app.ui.wallet.model.WalletViewEvent
import com.saqs.app.ui.wallet.viewmodel.WalletViewModel
import com.saqs.app.util.DateUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class WalletFragment : Fragment(), TicketItemAdapter.TicketItemClickListener {

    private lateinit var viewAdapter: TicketItemAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    @Inject lateinit var viewModel: WalletViewModel
    private lateinit var viewEvent: WalletViewEvent

    private var _binding: FragmentWalletBinding? = null
    private val binding get() = _binding!!

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.attachEvents(this)
        _binding = FragmentWalletBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupEventItemAdapter()
        initViewEffects()
        initViewStates()
    }

    fun attachViewEvents(viewEvent: WalletViewEvent) {
        this.viewEvent = viewEvent
    }

    private fun initViewEffects() {
    }

    private fun initViewStates() {
        viewModel.state.ticketsWithEvents.onEach { state ->
            viewAdapter.submitList(state.sortedBy { DateUtils.fromTimestamp(it.event.date) })
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    override fun onTicketItemClicked(ticketItem: Ticket) {
        // TODO
    }

    private fun setupEventItemAdapter() {
        viewManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        viewAdapter = TicketItemAdapter(this, requireContext())

        binding.recyclerViewTicketItems.apply {
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }
}
