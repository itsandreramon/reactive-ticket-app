/*
 * Copyright 2020 - André Thiele
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.data

import com.saqs.app.domain.Event
import com.saqs.app.util.Result
import kotlinx.coroutines.flow.Flow

interface FirebaseSource {
    fun observeAllEvents(): Flow<List<Event>>

    suspend fun bookEvent(event: Event, amount: Int): Result<Double>
}
