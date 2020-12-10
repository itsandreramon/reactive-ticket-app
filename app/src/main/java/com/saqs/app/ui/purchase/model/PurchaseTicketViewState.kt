/*
 * Copyright 2020 - André Thiele, Allan Fodi, Hüseyin Celik, Bertin Junior Wagueu Nkepgang
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.ui.purchase.model

import android.view.View
import com.saqs.app.domain.Event
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class _PurchaseTicketViewState(
    val _selectedEventId: MutableStateFlow<String?> = MutableStateFlow(null),
    val _selectedEvent: MutableStateFlow<Event?> = MutableStateFlow(null),
    val _layoutAmountVisible: MutableStateFlow<Int> = MutableStateFlow(View.VISIBLE),
    val _dialogLoadingVisible: MutableStateFlow<Boolean> = MutableStateFlow(false),
    val _buttonPurchaseEnabled: MutableStateFlow<Boolean> = MutableStateFlow(true)
)

data class PurchaseTicketViewState(private val _state: _PurchaseTicketViewState) {
    val selectedEventId: StateFlow<String?> = _state._selectedEventId
    val selectedEvent: StateFlow<Event?> = _state._selectedEvent
    val layoutAmountVisible: MutableStateFlow<Int> = _state._layoutAmountVisible
    val dialogLoadingVisible: MutableStateFlow<Boolean> = _state._dialogLoadingVisible
    val buttonPurchaseEnabled: MutableStateFlow<Boolean> = _state._buttonPurchaseEnabled
}
