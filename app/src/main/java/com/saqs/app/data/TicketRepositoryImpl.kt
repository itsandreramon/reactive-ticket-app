/*
 * Copyright 2020 - André Thiele
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.data

import com.saqs.app.domain.Ticket
import kotlinx.coroutines.flow.Flow

class TicketRepositoryImpl(
    private val dispatcherProvider: CoroutinesDispatcherProvider
) : TicketRepository {

    override fun addTicket(ticket: Ticket) {
        InMemoryDatabase.tickets.value = buildList {
            addAll(InMemoryDatabase.tickets.value)
            add(ticket)
        }
    }

    override fun getAll(): Flow<List<Ticket>> {
        return InMemoryDatabase.tickets
    }

    companion object {

        @Volatile private var instance: TicketRepositoryImpl? = null

        fun getInstance(
            dispatcherProvider: CoroutinesDispatcherProvider
        ) = instance
            ?: TicketRepositoryImpl(
                dispatcherProvider
            ).also { instance = it }
    }
}
