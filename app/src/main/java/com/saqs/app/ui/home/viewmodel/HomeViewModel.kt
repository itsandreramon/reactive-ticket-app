/*
 * Copyright 2020 - André Thiele
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.ui.home.viewmodel

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saqs.app.ui.home.HomeFragment
import com.saqs.app.ui.home.model.*
import com.saqs.app.ui.home.model.HomeViewEffectType.ShowSnackBarEffect
import com.saqs.app.ui.home.model.HomeViewEventType.NavigateEventItem
import com.saqs.app.ui.home.model.HomeViewEventType.NavigateHello
import kotlinx.coroutines.launch
import timber.log.Timber

class HomeViewModel @ViewModelInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel(), HomeViewEvent {

    private val _state = _HomeViewState()
    val state = HomeViewState(_state)

    private val _effect = _HomeViewEffect()
    val effect = HomeViewEffect(_effect)

    fun attachEvents(fragment: HomeFragment) {
        fragment.attachViewEvents(this)
    }

    override fun navigateHello(event: NavigateHello) {
        viewModelScope.launch {
            _effect._showSnackBar.emit(ShowSnackBarEffect)
            _state._counter.emit(state.counter.value + 1)
        }
    }

    override fun navigateEventItem(event: NavigateEventItem) {
        viewModelScope.launch {
            Timber.e("Navigate to event!")
        }
    }
}
