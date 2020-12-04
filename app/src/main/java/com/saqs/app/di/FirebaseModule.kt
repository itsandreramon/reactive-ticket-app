/*
 * Copyright 2020 - André Thiele
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.di

import com.saqs.app.data.FirebaseSource
import com.saqs.app.data.FirebaseSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object FirebaseModule {

    @Provides
    fun provideFirebaseSource(): FirebaseSource = FirebaseSourceImpl()
}
