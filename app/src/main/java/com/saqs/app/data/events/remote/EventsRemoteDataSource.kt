package com.saqs.app.data.events.remote

import com.saqs.app.domain.Event
import com.saqs.app.util.Result
import kotlinx.coroutines.flow.Flow

interface EventsRemoteDataSource {
    fun getAll(): Flow<List<Event>>

    suspend fun bookEvent(event: Event, amount: Int): Result<Double>
}