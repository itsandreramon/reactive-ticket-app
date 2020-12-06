/*
 * Copyright 2020 - André Thiele
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.data

import com.saqs.app.db.TicketRoomDao
import com.saqs.app.domain.Ticket
import com.saqs.app.util.Lce
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.withContext

class TicketRepositoryImpl(
    private val dispatcherProvider: CoroutinesDispatcherProvider,
    private val ticketRoomDao: TicketRoomDao
) : TicketRepository {

    override suspend fun addTicket(ticket: Ticket) {
        withContext(dispatcherProvider.db) {
            ticketRoomDao.add(ticket)
        }
    }

    override fun getAll() = channelFlow<Lce<List<Ticket>>> {
        ticketRoomDao.getAll()
            .onStart { send(Lce.Loading()) }
            .catch { send(Lce.Error(it)) }
            .collect { send(Lce.Content(it)) }
    }.flowOn(dispatcherProvider.db)
}
