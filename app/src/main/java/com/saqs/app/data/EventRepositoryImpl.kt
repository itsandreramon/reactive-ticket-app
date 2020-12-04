/*
 * Copyright 2020 - André Thiele
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.data

import com.saqs.app.domain.Event
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class EventRepositoryImpl private constructor(
    private val dispatcherProvider: CoroutinesDispatcherProvider,
    private val firebaseSource: FirebaseSource
) : EventRepository {

    override fun addEvent(event: Event) {
        InMemoryDatabase.events.value = buildList {
            addAll(InMemoryDatabase.events.value)
            add(event)
        }
    }

    override fun getAll(): Flow<List<Event>> {
        return InMemoryDatabase.events
    }

    override fun getAllRemote(): Flow<Event> {
        return firebaseSource.observeEvents()
            .flowOn(dispatcherProvider.io)
    }

    override fun getById(id: String): Event {
        return InMemoryDatabase.events.value.first { it.id == id }
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
