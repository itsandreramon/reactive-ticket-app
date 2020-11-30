/*
 * Copyright 2020 - André Thiele
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.data

import com.saqs.app.domain.Event
import kotlin.random.Random
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeData(
    val eventGenerator: Flow<Event> = flow {
        (0..10).forEach { _ ->
            delay(2000)
            emit(
                Event(
                    availableTickets = Random.nextInt(0, 100),
                    availableTicketsOverall = 100
                )
            )
        }
    }
)
