/*
 * Copyright 2020 - André Thiele
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.ui.home.model

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class _HomeViewState(
    val _counter: MutableStateFlow<Int> = MutableStateFlow(0)
)

data class HomeViewState(private val _state: _HomeViewState) {
    val counter: StateFlow<Int> = _state._counter
}
