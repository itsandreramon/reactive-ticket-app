package com.saqs.app.data.tickets.local

import com.saqs.app.domain.Ticket
import kotlinx.coroutines.flow.Flow

interface TicketsLocalDataSource {
    fun observeAll(): Flow<List<Ticket>>

    suspend fun insert(ticket: Ticket)
}