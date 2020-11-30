/*
 * Copyright 2020 - André Thiele
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.ui.explore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.saqs.app.databinding.FragmentExploreBinding
import com.saqs.app.domain.Event
import com.saqs.app.ui.explore.adapter.EventItemAdapter
import com.saqs.app.ui.explore.model.ExploreViewEvent
import com.saqs.app.ui.explore.model.HomeViewEventType.NavigateEventItem
import com.saqs.app.ui.explore.viewmodel.ExploreViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class ExploreFragment : Fragment(), EventItemAdapter.EventItemClickListener {

    private lateinit var viewAdapter: EventItemAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    private val viewModel by viewModels<ExploreViewModel>()
    private lateinit var viewEvent: ExploreViewEvent

    private var _binding: FragmentExploreBinding? = null
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
        _binding = FragmentExploreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupEventItemAdapter()
        initViewEffects()
        initViewStates()
    }

    fun attachViewEvents(viewEvent: ExploreViewEvent) {
        this.viewEvent = viewEvent
    }

    private fun initViewEffects() {
    }

    private fun initViewStates() {
        viewModel.state.events.onEach { state ->
            viewAdapter.submitList(state.sortedBy { it.date })
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    override fun onEventItemClicked(eventItem: Event) {
        viewEvent.navigateEventItem(NavigateEventItem(eventItem))
    }

    private fun showToastMessage() {
        Toast.makeText(requireContext(), "Hello SharedFlow", Toast.LENGTH_SHORT).show()
    }

    private fun setupEventItemAdapter() {
        viewManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        viewAdapter = EventItemAdapter(this, requireContext())

        binding.recyclerViewEventItems.apply {
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }
}
