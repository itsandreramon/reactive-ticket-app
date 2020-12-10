/*
 * Copyright 2020 - André Thiele
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.data.events.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.saqs.app.domain.Event
import kotlinx.coroutines.flow.Flow

@Dao
interface EventsRoomDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(event: Event)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(events: List<Event>)

    @Query("SELECT * FROM events")
    fun observeAll(): Flow<List<Event>>

    @Query("SELECT * FROM events WHERE id = :id LIMIT 1")
    fun observeById(id: String): Flow<Event>
}
