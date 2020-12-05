/*
 * Copyright 2020 - André Thiele
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.data

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.saqs.app.domain.Event
import com.saqs.app.domain.Ticket
import com.saqs.app.util.Result
import kotlinx.coroutines.flow.Flow

interface FirebaseSource {
    val firestore: FirebaseFirestore

    fun observeEvents(): Flow<Event>

    fun observeTickets(): Flow<Ticket>

    suspend fun updateEvent(event: Event) : Result<Void>

    suspend fun addTicket(ticket: Ticket) : Result<DocumentReference>
}
