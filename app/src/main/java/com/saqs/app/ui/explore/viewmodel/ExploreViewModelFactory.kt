/*
 * Copyright 2020 - André Thiele
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.ui.explore.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.saqs.app.data.CoroutinesDispatcherProvider
import com.saqs.app.data.EventRepository
import javax.inject.Inject

class ExploreViewModelFactory @Inject constructor(
    private val repository: EventRepository,
    private val dispatcherProvider: CoroutinesDispatcherProvider
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ExploreViewModel(
            repository,
            dispatcherProvider
        ) as T
    }
}
