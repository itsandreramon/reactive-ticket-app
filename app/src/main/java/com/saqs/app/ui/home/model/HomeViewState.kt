/*
 * Copyright 2020 - André Thiele
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.ui.home.model

import com.saqs.app.domain.Event
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class _HomeViewState(
    val _events: MutableStateFlow<List<Event>> = MutableStateFlow(listOf())
)

data class HomeViewState(private val _state: _HomeViewState) {
    val events: StateFlow<List<Event>> = _state._events
}
