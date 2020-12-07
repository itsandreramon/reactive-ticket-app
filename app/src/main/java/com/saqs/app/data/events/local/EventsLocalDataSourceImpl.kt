/*
 * Copyright 2020 - André Thiele, Allan Fodi, Hüseyin Celik, Bertin Junior Wagueu Nkepgang
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.data.events.local

import com.saqs.app.data.CoroutinesDispatcherProvider
import com.saqs.app.domain.Event
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

class EventsLocalDataSourceImpl internal constructor(
    private val eventsRoomDao: EventsRoomDao,
    private val dispatcherProvider: CoroutinesDispatcherProvider
) : EventsLocalDataSource {

    override fun getById(id: String): Flow<Event> {
        return eventsRoomDao.observeById(id)
            .flowOn(dispatcherProvider.db)
    }

    override fun getAll(): Flow<List<Event>> {
        return eventsRoomDao.observeAll()
            .flowOn(dispatcherProvider.db)
    }

    override suspend fun insert(event: Event) {
        withContext(dispatcherProvider.db) {
            eventsRoomDao.insert(event)
        }
    }

    override suspend fun insertAll(events: List<Event>) {
        withContext(dispatcherProvider.db) {
            eventsRoomDao.insertAll(events)
        }
    }
}
