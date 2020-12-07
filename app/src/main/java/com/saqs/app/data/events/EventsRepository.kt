/*
 * Copyright 2020 - André Thiele, Allan Fodi, Hüseyin Celik, Bertin Junior Wagueu Nkepgang
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.data.events

import com.saqs.app.domain.Event
import com.saqs.app.util.Lce
import com.saqs.app.util.Result
import kotlinx.coroutines.flow.Flow

interface EventsRepository {
    fun getById(id: String): Flow<Event>

    fun getAll(): Flow<Lce<List<Event>>>

    fun getAllRemote(): Flow<Lce<List<Event>>>

    suspend fun insert(event: Event)

    suspend fun bookEventRemote(event: Event, amount: Int): Result<Double>
}
