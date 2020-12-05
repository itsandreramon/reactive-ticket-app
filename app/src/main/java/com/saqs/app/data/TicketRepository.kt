/*
 * Copyright 2020 - André Thiele
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.data

import com.saqs.app.domain.Ticket
import com.saqs.app.util.Lce
import kotlinx.coroutines.flow.Flow

interface TicketRepository {
    suspend fun addTicket(ticket: Ticket)
    fun getAll(): Flow<Lce<List<Ticket>>>
}
