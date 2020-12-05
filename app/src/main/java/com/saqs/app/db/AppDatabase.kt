package com.saqs.app.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.saqs.app.domain.Event

@Database(entities = [Event::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun eventDao(): EventRoomDao
}
