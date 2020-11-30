/*
 * Copyright 2020 - André Thiele
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.saqs.app.domain.Event
import com.saqs.app.domain.Ticket
import com.saqs.app.util.LOCAL_DATABASE_NAME

@Database(entities = [Event::class, Ticket::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun eventDao(): EventRoomDao
    abstract fun ticketDao(): TicketRoomDao

    companion object {

        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, LOCAL_DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}
