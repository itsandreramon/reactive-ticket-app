package com.saqs.app.data

import com.saqs.app.domain.Ticket
import kotlinx.coroutines.flow.MutableStateFlow

interface DataSource {
    val tickets: MutableStateFlow<List<Ticket>>
}