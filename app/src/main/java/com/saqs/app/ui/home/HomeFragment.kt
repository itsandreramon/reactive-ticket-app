/*
 * Copyright 2020 - André Thiele
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.ui.home

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
import com.saqs.app.data.FakeData
import com.saqs.app.databinding.FragmentHomeBinding
import com.saqs.app.domain.Event
import com.saqs.app.ui.home.adapter.EventItemAdapter
import com.saqs.app.ui.home.model.HomeViewEvent
import com.saqs.app.ui.home.model.HomeViewEventType
import com.saqs.app.ui.home.model.HomeViewEventType.NavigateEventItem
import com.saqs.app.ui.home.model.HomeViewEventType.NavigateHello
import com.saqs.app.ui.home.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class HomeFragment : Fragment(), EventItemAdapter.EventItemClickListener {

    private lateinit var viewAdapter: EventItemAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    private val viewModel by viewModels<HomeViewModel>()
    private lateinit var viewEvent: HomeViewEvent

    private var _binding: FragmentHomeBinding? = null
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
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewEffects()
        initViewStates()

        setupEventItemAdapter()

        viewAdapter.submitList(FakeData().events)
    }

    fun attachViewEvents(viewEvent: HomeViewEvent) {
        this.viewEvent = viewEvent
    }

    private fun initViewEffects() {

    }

    private fun initViewStates() {
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
