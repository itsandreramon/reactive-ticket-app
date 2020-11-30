/*
 * Copyright 2020 - André Thiele
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.data.database

import androidx.room.Dao
import androidx.room.Query
import com.saqs.app.domain.Event
import kotlinx.coroutines.flow.Flow

@Dao
interface TicketRoomDao {

    @Query("SELECT * FROM events")
    fun getAll(): Flow<List<Event>>
}
