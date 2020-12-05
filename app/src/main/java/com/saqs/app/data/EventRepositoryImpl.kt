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
import com.saqs.app.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.withContext

class EventRepositoryImpl private constructor(
    private val dispatcherProvider: CoroutinesDispatcherProvider,
    private val firebaseSource: FirebaseSource
) : EventRepository {

    private val dataSource: DataSource
        get() = InMemoryDatabase

    override fun getAll() = channelFlow<Lce<List<Event>>> {
        dataSource.events
            .onStart { send(Lce.Loading()) }
            .catch { send(Lce.Error(it)) }
            .collect { send(Lce.Content(it)) }
    }.flowOn(dispatcherProvider.db)

    override fun observeEventsRemote(): Flow<Event> {
        return firebaseSource.observeEvents()
            .flowOn(dispatcherProvider.io)
    }

    override suspend fun bookEventRemote(
        event: Event,
        amount: Int
    ): Result<Task<Double>> {
        return withContext(dispatcherProvider.io) {
            firebaseSource.bookEvent(event, amount)
        }
    }

    override suspend fun addEvent(event: Event) {
        withContext(dispatcherProvider.db) {
            dataSource.events.value = buildList {
                addAll(dataSource.events.value)
                add(event)
            }
        }
    }

    override suspend fun getById(id: String): Event? {
        return withContext(dispatcherProvider.db) {
            dataSource.events.value.firstOrNull { it.id == id }
        }
    }

    companion object {

        @Volatile private var instance: EventRepositoryImpl? = null

        fun getInstance(
            dispatcherProvider: CoroutinesDispatcherProvider,
            firebaseSource: FirebaseSource
        ) = instance
            ?: EventRepositoryImpl(
                dispatcherProvider,
                firebaseSource
            ).also { instance = it }
    }
}
