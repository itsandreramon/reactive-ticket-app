/*
 * Copyright 2020 - André Thiele
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.data

import com.google.firebase.firestore.DocumentReference
import com.saqs.app.domain.Ticket
import com.saqs.app.util.Lce
import com.saqs.app.util.Result
import kotlinx.coroutines.flow.Flow

interface TicketRepository {
    val dataSource: DataSource

    suspend fun addTicket(ticket: Ticket)

    suspend fun addTicketRemote(ticket: Ticket) : Result<DocumentReference>

    fun getAll(): Flow<Lce<List<Ticket>>>

    fun observeTicketsRemote(): Flow<Ticket>
}
