/*
 * Copyright 2020 - André Thiele
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.data

import com.saqs.app.domain.Ticket
import com.saqs.app.util.Lce
<<<<<<< HEAD
=======
import kotlinx.coroutines.flow.Flow
>>>>>>> 42c26509c8c8f19124152731e691a3eb9d3f28fe
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.withContext

class TicketRepositoryImpl(
    private val dispatcherProvider: CoroutinesDispatcherProvider,
    private val firebaseSource: FirebaseSource
) : TicketRepository {

<<<<<<< HEAD
    override val dataSource: DataSource
=======
    private val dataSource: DataSource
>>>>>>> 42c26509c8c8f19124152731e691a3eb9d3f28fe
        get() = InMemoryDatabase

    override suspend fun addTicket(ticket: Ticket) {
        withContext(dispatcherProvider.db) {
            dataSource.tickets.value = buildList {
                addAll(dataSource.tickets.value)
                add(ticket)
            }
        }
    }

    override fun getAll() = channelFlow<Lce<List<Ticket>>> {
        dataSource.tickets
            .onStart { send(Lce.Loading()) }
            .catch { send(Lce.Error(it)) }
            .collect { send(Lce.Content(it)) }
    }.flowOn(dispatcherProvider.db)

    companion object {

        @Volatile private var instance: TicketRepositoryImpl? = null

        fun getInstance(
            dispatcherProvider: CoroutinesDispatcherProvider,
            firebaseSource: FirebaseSource
        ) = instance
            ?: TicketRepositoryImpl(
                dispatcherProvider,
                firebaseSource
            ).also { instance = it }
    }
}
