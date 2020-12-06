/*
 * Copyright 2020 - André Thiele
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.data.events.local

import com.saqs.app.domain.Event
import kotlinx.coroutines.flow.Flow

interface EventsLocalDataSource {
    fun getById(id: String): Flow<Event>

    fun getAll(): Flow<List<Event>>

    suspend fun insert(event: Event)

    suspend fun insertAll(events: List<Event>)
}
