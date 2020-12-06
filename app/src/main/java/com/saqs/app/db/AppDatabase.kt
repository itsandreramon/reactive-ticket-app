/*
 * Copyright 2020 - André Thiele
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.saqs.app.data.events.local.EventsRoomDao
import com.saqs.app.data.tickets.local.TicketsRoomDao
import com.saqs.app.domain.Event
import com.saqs.app.domain.Ticket

@Database(
    entities = [Event::class, Ticket::class],
    version = 1, exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun eventDao(): EventsRoomDao
    abstract fun ticketDao(): TicketsRoomDao
}
