package com.saqs.app.data

import com.saqs.app.domain.Event
import kotlinx.coroutines.flow.MutableStateFlow

object InMemoryDatabase {
    val events: MutableStateFlow<List<Event>> = MutableStateFlow(listOf())
}