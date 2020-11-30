package com.saqs.app.data

import com.saqs.app.domain.Event
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.UUID
import kotlin.random.Random
import kotlin.time.Duration

class FakeData(
    val eventGenerator: Flow<Event> = flow {
        while (true) {
            delay(1000)
            emit(
                Event(
                    name = UUID.randomUUID().toString(),
                    availableTicketsInPercent = Random.nextDouble(0.0, 1.0).toFloat()
                )
            )
        }
    }
)