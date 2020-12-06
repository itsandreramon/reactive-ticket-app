/*
 * Copyright 2020 - André Thiele
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.data

import com.saqs.app.db.EventRoomDao
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

class EventRepositoryImpl(
    private val dispatcherProvider: CoroutinesDispatcherProvider,
    private val eventRoomDao: EventRoomDao,
    private val firebaseSource: FirebaseSource
) : EventRepository {

    override fun getById(id: String): Flow<Event> {
        return eventRoomDao.getById(id)
            .flowOn(dispatcherProvider.db)
    }

    override fun getAll() = channelFlow<Lce<List<Event>>> {
        eventRoomDao.getAll()
            .onStart { send(Lce.Loading()) }
            .catch { send(Lce.Error(it)) }
            .collect { send(Lce.Content(it)) }
    }.flowOn(dispatcherProvider.db)

    override suspend fun insert(event: Event) {
        withContext(dispatcherProvider.db) {
            eventRoomDao.add(event)
        }
    }

    override fun getAllRemote() = channelFlow<Lce<List<Event>>> {
        firebaseSource.observeEvents()
            .onStart { send(Lce.Loading()) }
            .catch { send(Lce.Error(it)) }
            .collect {
                eventRoomDao.addAll(it) // cache locally
                send(Lce.Content(it)) // emit successful content
            }
    }.flowOn(dispatcherProvider.io)

    override suspend fun bookEventRemote(event: Event, amount: Int): Result<Double> {
        return withContext(dispatcherProvider.io) {
            firebaseSource.bookEvent(event, amount)
        }
    }
}
