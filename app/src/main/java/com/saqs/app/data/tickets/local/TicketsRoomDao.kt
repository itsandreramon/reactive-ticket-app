/*
 * Copyright 2020 - André Thiele
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.data.tickets.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.saqs.app.domain.Ticket
import kotlinx.coroutines.flow.Flow

@Dao
interface TicketsRoomDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(event: Ticket)

    @Query("SELECT * FROM tickets")
    fun observeAll(): Flow<List<Ticket>>
}
