/*
 * Copyright 2020 - André Thiele
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.saqs.app.domain.Ticket
import kotlinx.coroutines.flow.Flow

@Dao
interface TicketRoomDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(event: Ticket)

    @Query("SELECT * FROM tickets")
    fun getAll(): Flow<List<Ticket>>
}
