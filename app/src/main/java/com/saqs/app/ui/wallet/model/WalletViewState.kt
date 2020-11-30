/*
 * Copyright 2020 - André Thiele
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.ui.wallet.model

import com.saqs.app.domain.Ticket
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class _WalletViewState(
    val _tickets: MutableStateFlow<List<Ticket>> = MutableStateFlow(listOf())
)

data class WalletViewState(private val _state: _WalletViewState) {
    val tickets: StateFlow<List<Ticket>> = _state._tickets
}
