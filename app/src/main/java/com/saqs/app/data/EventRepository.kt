/*
 * Copyright 2020 - André Thiele
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.data

import com.google.android.gms.tasks.Task
import com.saqs.app.domain.Event
import com.saqs.app.util.Lce
<<<<<<< HEAD
import kotlinx.coroutines.flow.Flow

interface EventRepository {
    val dataSource: DataSource

    suspend fun addEvent(event: Event)

    suspend fun getById(id: String): Event?

    fun getAll(): Flow<Lce<List<Event>>>

    fun getAllRemote(): Flow<Event>
=======
import com.saqs.app.util.Result
import kotlinx.coroutines.flow.Flow

interface EventRepository {
    suspend fun getById(id: String): Event?
    fun getAll(): Flow<Lce<List<Event>>>
    suspend fun addEvent(event: Event)
    suspend fun bookEventRemote(event: Event, amount: Int): Result<Task<Double>>
    fun observeEventsRemote(): Flow<Event>
>>>>>>> 42c26509c8c8f19124152731e691a3eb9d3f28fe
}
