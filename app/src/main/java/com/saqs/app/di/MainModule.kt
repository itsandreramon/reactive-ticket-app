/*
 * Copyright 2020 - André Thiele
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.di

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.saqs.app.data.EventRepository
import com.saqs.app.data.EventRepositoryImpl
import com.saqs.app.data.FirebaseSource
import com.saqs.app.ui.explore.viewmodel.ExploreViewModel
import com.saqs.app.ui.explore.viewmodel.ExploreViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
class MainModule {

    @Provides
    fun provideEventRepository(
        firebaseSource: FirebaseSource
    ): EventRepository = EventRepositoryImpl.getInstance(firebaseSource)

    @Provides
    fun provideExploreViewModel(
        factory: ExploreViewModelFactory,
        activity: FragmentActivity
    ): ExploreViewModel = ViewModelProvider(activity, factory).get(ExploreViewModel::class.java)
}
