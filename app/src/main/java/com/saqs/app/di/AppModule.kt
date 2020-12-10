/*
 * Copyright 2020 - André Thiele, Allan Fodi, Hüseyin Celik, Bertin Junior Wagueu Nkepgang
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.di

import android.content.Context
import androidx.room.Room
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.saqs.app.data.CoroutinesDispatcherProvider
import com.saqs.app.data.FirebaseSource
import com.saqs.app.data.FirebaseSourceImpl
import com.saqs.app.data.events.EventsRepository
import com.saqs.app.data.events.EventsRepositoryImpl
import com.saqs.app.data.events.local.EventsLocalDataSource
import com.saqs.app.data.events.local.EventsLocalDataSourceImpl
import com.saqs.app.data.events.remote.EventsRemoteDataSource
import com.saqs.app.data.events.remote.EventsRemoteDataSourceImpl
import com.saqs.app.data.tickets.TicketsRepository
import com.saqs.app.data.tickets.TicketsRepositoryImpl
import com.saqs.app.data.tickets.local.TicketsLocalDataSource
import com.saqs.app.data.tickets.local.TicketsLocalDataSourceImpl
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
    fun provideFirebaseFirestore(): FirebaseFirestore {
        return Firebase.firestore
    }

    @Singleton
    @Provides
    fun provideFirebaseSource(
        firestore: FirebaseFirestore
    ): FirebaseSource {
        return FirebaseSourceImpl(firestore)
    }

    @Singleton
    @Provides
    fun provideEventsRepository(
        eventsLocalDataSource: EventsLocalDataSource,
        eventsRemoteDataSource: EventsRemoteDataSource
    ): EventsRepository = EventsRepositoryImpl(eventsLocalDataSource, eventsRemoteDataSource)

    @Singleton
    @Provides
    fun provideTicketsRepository(
        ticketsLocalDataSource: TicketsLocalDataSource
    ): TicketsRepository = TicketsRepositoryImpl(ticketsLocalDataSource)

    @Singleton
    @Provides
    fun provideTicketsLocalDataSource(
        appDatabase: AppDatabase,
        dispatcherProvider: CoroutinesDispatcherProvider
    ): TicketsLocalDataSource = TicketsLocalDataSourceImpl(appDatabase.ticketDao(), dispatcherProvider)

    @Singleton
    @Provides
    fun provideEventsLocalDataSource(
        appDatabase: AppDatabase,
        dispatcherProvider: CoroutinesDispatcherProvider
    ): EventsLocalDataSource = EventsLocalDataSourceImpl(appDatabase.eventDao(), dispatcherProvider)

    @Singleton
    @Provides
    fun provideEventsRemoteDataSource(
        firebaseSource: FirebaseSource,
        dispatcherProvider: CoroutinesDispatcherProvider
    ): EventsRemoteDataSource = EventsRemoteDataSourceImpl(firebaseSource, dispatcherProvider)

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
