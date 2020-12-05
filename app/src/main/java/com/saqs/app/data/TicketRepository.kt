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
<<<<<<< HEAD
    val dataSource: DataSource

    suspend fun addTicket(ticket: Ticket)

=======
    suspend fun addTicket(ticket: Ticket)
>>>>>>> 42c26509c8c8f19124152731e691a3eb9d3f28fe
    fun getAll(): Flow<Lce<List<Ticket>>>
}
