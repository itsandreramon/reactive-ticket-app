/*
 * Copyright 2020 - André Thiele
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.data.tickets.local

import com.saqs.app.data.CoroutinesDispatcherProvider
import com.saqs.app.domain.Ticket
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

class TicketsLocalDataSourceImpl internal constructor(
    private val ticketsRoomDao: TicketsRoomDao,
    private val dispatcherProvider: CoroutinesDispatcherProvider
) : TicketsLocalDataSource {

    override fun observeAll(): Flow<List<Ticket>> {
        return ticketsRoomDao.observeAll()
            .flowOn(dispatcherProvider.db)
    }

    override suspend fun insert(ticket: Ticket) {
        withContext(dispatcherProvider.db) {
            ticketsRoomDao.insert(ticket)
        }
    }
}
