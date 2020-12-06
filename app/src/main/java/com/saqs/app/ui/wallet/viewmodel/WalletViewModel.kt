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
import com.saqs.app.data.events.EventsRepository
import com.saqs.app.data.tickets.TicketsRepository
import com.saqs.app.domain.TicketWithEvent
import com.saqs.app.ui.wallet.WalletFragment
import com.saqs.app.ui.wallet.model.WalletViewEvent
import com.saqs.app.ui.wallet.model.WalletViewState
import com.saqs.app.ui.wallet.model._WalletViewState
import com.saqs.app.util.Lce
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class WalletViewModel @ViewModelInject constructor(
    private val eventsRepository: EventsRepository,
    private val ticketsRepository: TicketsRepository
) : ViewModel(), WalletViewEvent {

    private val _state = _WalletViewState()
    val state = WalletViewState(_state)

    fun attachEvents(fragment: WalletFragment) {
        fragment.attachViewEvents(this)
    }

    init {
        ticketsRepository.observeAll().onEach { lce ->
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

        eventsRepository.getAll().onEach { lce ->
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

        ticketsRepository.observeAll().combine(state.events) { ticketsLce, events ->
            when (ticketsLce) {
                is Lce.Loading -> {
                    // TODO
                }
                is Lce.Error -> {
                    // TODO
                }
                is Lce.Content -> {
                    val tickets = ticketsLce.packet
                    val ticketsWithEvents = ticketsLce.packet
                        .distinctBy { it.eventId }
                        .mapNotNull { ticket ->
                            events.firstOrNull { event -> ticket.eventId == event.id }
                                ?.let { event ->
                                    val amountTicketsForEvent =
                                        tickets.count { it.eventId == event.id }
                                    TicketWithEvent(ticket, event, amountTicketsForEvent)
                                }
                        }

                    _state._ticketsWithEvents.value = ticketsWithEvents
                }
            }
        }.launchIn(viewModelScope)
    }
}
