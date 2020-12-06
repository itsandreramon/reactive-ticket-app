/*
 * Copyright 2020 - André Thiele
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.data.events.remote

import com.saqs.app.data.CoroutinesDispatcherProvider
import com.saqs.app.data.FirebaseSource
import com.saqs.app.domain.Event
import com.saqs.app.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

class EventsRemoteDataSourceImpl internal constructor(
    private val firebaseSource: FirebaseSource,
    private val dispatcherProvider: CoroutinesDispatcherProvider
) : EventsRemoteDataSource {

    override fun getAll(): Flow<List<Event>> {
        return firebaseSource.observeAllEvents()
            .flowOn(dispatcherProvider.io)
    }

    override suspend fun bookEvent(event: Event, amount: Int): Result<Double> {
        return withContext(dispatcherProvider.io) {
            firebaseSource.bookEvent(event, amount)
        }
    }
}
