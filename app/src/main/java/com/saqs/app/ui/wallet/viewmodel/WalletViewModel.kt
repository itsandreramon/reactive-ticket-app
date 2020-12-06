/*
 * Copyright 2020 - André Thiele
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.ui.wallet.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saqs.app.data.EventRepository
import com.saqs.app.data.TicketRepository
import com.saqs.app.domain.TicketWithEvent
import com.saqs.app.ui.wallet.WalletFragment
import com.saqs.app.ui.wallet.model.WalletViewEvent
import com.saqs.app.ui.wallet.model.WalletViewState
import com.saqs.app.ui.wallet.model._WalletViewState
import com.saqs.app.util.DateUtils
import com.saqs.app.util.Lce
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class WalletViewModel @ViewModelInject constructor(
    private val eventRepository: EventRepository,
    private val ticketRepository: TicketRepository
) : ViewModel(), WalletViewEvent {

    private val _state = _WalletViewState()
    val state = WalletViewState(_state)

    fun attachEvents(fragment: WalletFragment) {
        fragment.attachViewEvents(this)
    }

    init {
        ticketRepository.getAll().onEach { lce ->
            when (lce) {
                is Lce.Loading -> {
                    // TODO
                }
                is Lce.Error -> {
                    // TODO
                }
                is Lce.Content -> {
                    _state._tickets.value = lce.packet
                }
            }
        }.launchIn(viewModelScope)

        eventRepository.getAll().onEach { lce ->
            when (lce) {
                is Lce.Loading -> {
                    // TODO
                }
                is Lce.Error -> {
                    // TODO
                }
                is Lce.Content -> {
                    _state._events.value = lce.packet
                }
            }
        }.launchIn(viewModelScope)

        ticketRepository.getAll().combine(state.events) { ticketsLce, events ->
            when (ticketsLce) {
                is Lce.Loading -> {
                    // TODO
                }
                is Lce.Error -> {
                    // TODO
                }
                is Lce.Content -> {
                    val tickets = ticketsLce.packet
                    val ticketsWithEvents = tickets
                        .distinctBy { it.eventId }
                        .map { ticket -> events
                            .first { it.id == ticket.eventId }
                            .let { event ->
                                val amount = tickets.count { it.eventId == event.id }
                                TicketWithEvent(ticket, event, amount)
                            }
                        }

                    _state._ticketsWithEvents.value = ticketsWithEvents
                        .sortedBy { DateUtils.fromTimestamp(it.event.date) }
                }
            }
        }.launchIn(viewModelScope)
    }
}
