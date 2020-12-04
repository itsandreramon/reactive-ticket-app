/*
 * Copyright 2020 - André Thiele
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.ui.wallet.model

import com.saqs.app.domain.Event
import com.saqs.app.domain.Ticket
import com.saqs.app.domain.TicketWithEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class _WalletViewState(
    val _tickets: MutableStateFlow<List<Ticket>> = MutableStateFlow(listOf()),
    val _events: MutableStateFlow<List<Event>> = MutableStateFlow(listOf()),
    val _ticketsWithEvents: MutableStateFlow<List<TicketWithEvent>> = MutableStateFlow(listOf())
)

data class WalletViewState(private val _state: _WalletViewState) {
    val tickets: StateFlow<List<Ticket>> = _state._tickets
    val events: StateFlow<List<Event>> = _state._events
    val ticketsWithEvents: StateFlow<List<TicketWithEvent>> = _state._ticketsWithEvents
}
