package com.saqs.app.data

import com.saqs.app.domain.Event
import com.saqs.app.domain.Ticket
import kotlinx.coroutines.flow.MutableStateFlow

interface DataSource {
    val events: MutableStateFlow<List<Event>>
    val tickets: MutableStateFlow<List<Ticket>>
}