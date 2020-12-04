/*
 * Copyright 2020 - André Thiele
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.data

import com.saqs.app.domain.Event
import com.saqs.app.util.Lce
import kotlinx.coroutines.flow.Flow

interface EventRepository {
    val dataSource: DataSource

    suspend fun addEvent(event: Event)

    suspend fun getById(id: String): Event?

    fun getAll(): Flow<Lce<List<Event>>>

    fun getAllRemote(): Flow<Event>
}
