/*
 * Copyright 2020 - André Thiele
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.di

import android.content.Context
import androidx.room.Room
import com.saqs.app.data.CoroutinesDispatcherProvider
import com.saqs.app.data.EventRepository
import com.saqs.app.data.EventRepositoryImpl
import com.saqs.app.data.FirebaseSource
import com.saqs.app.data.FirebaseSourceImpl
import com.saqs.app.data.TicketRepository
import com.saqs.app.data.TicketRepositoryImpl
import com.saqs.app.db.AppDatabase
import com.saqs.app.util.LOCAL_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideFirebaseSource(): FirebaseSource {
        return FirebaseSourceImpl()
    }

    @Singleton
    @Provides
    fun provideEventRepository(
        dispatcherProvider: CoroutinesDispatcherProvider,
        appDatabase: AppDatabase,
        firebaseSource: FirebaseSource
    ): EventRepository = EventRepositoryImpl(dispatcherProvider, appDatabase.eventDao(), firebaseSource)

    @Singleton
    @Provides
    fun provideTicketRepository(
        dispatcherProvider: CoroutinesDispatcherProvider,
        firebaseSource: FirebaseSource
    ): TicketRepository = TicketRepositoryImpl.getInstance(dispatcherProvider, firebaseSource)

    @Singleton
    @Provides
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            LOCAL_DATABASE_NAME
        ).build()
    }
}
