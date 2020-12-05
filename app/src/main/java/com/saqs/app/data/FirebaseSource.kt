/*
 * Copyright 2020 - André Thiele
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.data

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.saqs.app.domain.Event
import com.saqs.app.domain.Ticket
import com.saqs.app.util.Result
import kotlinx.coroutines.flow.Flow

interface FirebaseSource {
    val firestore: FirebaseFirestore

    fun observeEvents(): Flow<Event>

    fun observeTickets(): Flow<Ticket>

    suspend fun bookEvent(event: Event): Result<Task<Double>>
}
