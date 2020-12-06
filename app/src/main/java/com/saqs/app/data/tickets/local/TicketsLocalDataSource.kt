/*
 * Copyright 2020 - André Thiele
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.data.tickets.local

import com.saqs.app.domain.Ticket
import kotlinx.coroutines.flow.Flow

interface TicketsLocalDataSource {
    fun observeAll(): Flow<List<Ticket>>

    suspend fun insert(ticket: Ticket)
}
