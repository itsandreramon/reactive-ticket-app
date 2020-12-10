/*
 * Copyright 2020 - André Thiele
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.ui.explore.model

import com.saqs.app.domain.Event
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class _ExploreViewState(
    val _events: MutableStateFlow<List<Event>> = MutableStateFlow(listOf())
)

data class ExploreViewState(private val _state: _ExploreViewState) {
    val events: StateFlow<List<Event>> = _state._events
}
