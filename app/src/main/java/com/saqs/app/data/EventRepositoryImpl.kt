/*
 * Copyright 2020 - André Thiele
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.data

import com.saqs.app.domain.Event
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOn

class EventRepositoryImpl private constructor(
    private val dispatcherProvider: CoroutinesDispatcherProvider,
    private val firebaseSource: FirebaseSource
) : EventRepository {

    override val inMemoryDatabase: MutableStateFlow<List<Event>>
        get() = InMemoryDatabase.events

    override fun observeEvents(): Flow<Event> {
        return firebaseSource.observeEvents()
            .flowOn(dispatcherProvider.io)
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
