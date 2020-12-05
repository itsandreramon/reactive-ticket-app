/*
 * Copyright 2020 - André Thiele
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.di

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.saqs.app.data.CoroutinesDispatcherProvider
import com.saqs.app.data.EventRepository
import com.saqs.app.data.EventRepositoryImpl
import com.saqs.app.data.FirebaseSource
import com.saqs.app.data.TicketRepository
import com.saqs.app.data.TicketRepositoryImpl
import com.saqs.app.db.AppDatabase
import com.saqs.app.ui.explore.viewmodel.ExploreViewModel
import com.saqs.app.ui.explore.viewmodel.ExploreViewModelFactory
import com.saqs.app.ui.purchase.viewmodel.PurchaseTicketViewModel
import com.saqs.app.ui.purchase.viewmodel.PurchaseTicketViewModelFactory
import com.saqs.app.ui.wallet.viewmodel.WalletViewModel
import com.saqs.app.ui.wallet.viewmodel.WalletViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
class MainModule {

    @Provides
    fun provideEventRepository(
        dispatcherProvider: CoroutinesDispatcherProvider,
        appDatabase: AppDatabase,
        firebaseSource: FirebaseSource
    ): EventRepository = EventRepositoryImpl.getInstance(dispatcherProvider, appDatabase.eventDao(), firebaseSource)

    @Provides
    fun provideTicketRepository(
        dispatcherProvider: CoroutinesDispatcherProvider,
        firebaseSource: FirebaseSource
    ): TicketRepository = TicketRepositoryImpl.getInstance(dispatcherProvider, firebaseSource)

    @Provides
    fun provideExploreViewModel(
        factory: ExploreViewModelFactory,
        activity: FragmentActivity
    ): ExploreViewModel = ViewModelProvider(activity, factory).get(ExploreViewModel::class.java)

    @Provides
    fun provideWalletViewModel(
        factory: WalletViewModelFactory,
        activity: FragmentActivity
    ): WalletViewModel = ViewModelProvider(activity, factory).get(WalletViewModel::class.java)

    @Provides
    fun providePurchaseTicketViewModel(
        factory: PurchaseTicketViewModelFactory,
        activity: FragmentActivity
    ): PurchaseTicketViewModel =
        ViewModelProvider(activity, factory).get(PurchaseTicketViewModel::class.java)
}
