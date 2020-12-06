/*
 * Copyright 2020 - André Thiele
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.data.tickets

import com.saqs.app.data.tickets.local.TicketsLocalDataSource
import com.saqs.app.domain.Ticket
import com.saqs.app.util.Lce
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart

class TicketsRepositoryImpl(
    private val ticketsLocalDataSource: TicketsLocalDataSource
) : TicketsRepository {

    override suspend fun insert(ticket: Ticket) {
        ticketsLocalDataSource.insert(ticket)
    }

    override fun observeAll() = channelFlow<Lce<List<Ticket>>> {
        ticketsLocalDataSource.observeAll()
            .onStart { send(Lce.Loading()) }
            .catch { send(Lce.Error(it)) }
            .collect { send(Lce.Content(it)) }
    }
}
