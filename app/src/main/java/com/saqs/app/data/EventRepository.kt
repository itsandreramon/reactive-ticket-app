/*
 * Copyright 2020 - André Thiele
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.data

import com.saqs.app.domain.Event
import kotlinx.coroutines.flow.Flow

interface EventRepository {
    fun addEvent(event: Event)
    fun getById(id: String): Event
    fun getAll(): Flow<List<Event>>
    fun getAllRemote(): Flow<Event>
}
