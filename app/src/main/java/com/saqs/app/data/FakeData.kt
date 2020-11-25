package com.saqs.app.data

import com.saqs.app.domain.Event

class FakeData(
    val events: List<Event> = listOf(
        Event(availableTicketsInPercent = 0.9F),
        Event(availableTicketsInPercent = 0.35F),
        Event(availableTicketsInPercent = 0.1F)
    )
)