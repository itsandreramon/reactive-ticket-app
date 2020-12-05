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
import com.saqs.app.domain.Event
import kotlinx.coroutines.flow.Flow

@Dao
interface EventRoomDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(event: Event)

    @Query("SELECT * FROM events")
    fun getAll(): Flow<List<Event>>

    @Query("SELECT * FROM events WHERE id = :id LIMIT 1")
    fun getById(id: String): Flow<Event>
}
