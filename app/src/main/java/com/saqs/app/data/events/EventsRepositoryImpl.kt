/*
 * Copyright 2020 - André Thiele
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.data.events

import com.saqs.app.data.events.local.EventsLocalDataSource
import com.saqs.app.data.events.remote.EventsRemoteDataSource
import com.saqs.app.domain.Event
import com.saqs.app.util.Lce
import com.saqs.app.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart

class EventsRepositoryImpl(
    private val eventsLocalDataSource: EventsLocalDataSource,
    private val eventsRemoteDataSource: EventsRemoteDataSource
) : EventsRepository {

    override fun getById(id: String): Flow<Event> {
        return eventsLocalDataSource.getById(id)
    }

    override fun getAll() = channelFlow<Lce<List<Event>>> {
        eventsLocalDataSource.getAll()
            .onStart { send(Lce.Loading()) }
            .catch { send(Lce.Error(it)) }
            .collect { send(Lce.Content(it)) }
    }

    override fun getAllRemote() = channelFlow<Lce<List<Event>>> {
        eventsRemoteDataSource.getAll()
            .onStart { send(Lce.Loading()) }
            .catch { send(Lce.Error(it)) }
            .collect {
                eventsLocalDataSource.insertAll(it) // cache locally
                send(Lce.Content(it)) // emit successful content
            }
    }

    override suspend fun insert(event: Event) {
        eventsLocalDataSource.insert(event)
    }

    override suspend fun bookEventRemote(event: Event, amount: Int): Result<Double> {
        return eventsRemoteDataSource.bookEvent(event, amount)
    }
}
