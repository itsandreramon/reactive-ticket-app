/*
 * Copyright 2020 - André Thiele
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.data

<<<<<<< HEAD
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.saqs.app.domain.Event
import com.saqs.app.domain.Ticket
=======
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.saqs.app.domain.Event
>>>>>>> 42c26509c8c8f19124152731e691a3eb9d3f28fe
import com.saqs.app.util.Result
import kotlinx.coroutines.flow.Flow

interface FirebaseSource {
    val firestore: FirebaseFirestore
<<<<<<< HEAD

    fun observeEvents(): Flow<Event>

    fun observeTickets(): Flow<Ticket>

    suspend fun updateEvent(event: Event) : Result<Void>

    suspend fun addTicket(ticket: Ticket) : Result<DocumentReference>
=======
    fun observeEvents(): Flow<Event>
    suspend fun bookEvent(event: Event, amount: Int): Result<Task<Double>>
>>>>>>> 42c26509c8c8f19124152731e691a3eb9d3f28fe
}
