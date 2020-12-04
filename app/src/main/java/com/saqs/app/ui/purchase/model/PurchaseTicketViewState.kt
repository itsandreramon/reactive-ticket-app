/*
 * Copyright 2020 - André Thiele
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.ui.purchase.model

import com.saqs.app.domain.Event
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class _PurchaseTicketViewState(
    val _selectedEvent: MutableStateFlow<Event?> = MutableStateFlow(null)
)

data class PurchaseTicketViewState(private val _state: _PurchaseTicketViewState) {
    val selectedEvent: StateFlow<Event?> = _state._selectedEvent
}
