/*
 * Copyright 2020 - André Thiele, Allan Fodi, Hüseyin Celik, Bertin Junior Wagueu Nkepgang
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.ui.explore.viewmodel

import android.view.View
import androidx.annotation.VisibleForTesting
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saqs.app.data.events.EventsRepository
import com.saqs.app.ui.explore.ExploreFragment
import com.saqs.app.ui.explore.model.ExploreViewEffect
import com.saqs.app.ui.explore.model.ExploreViewEffectType.PurchaseTicketEffect
import com.saqs.app.ui.explore.model.ExploreViewEffectType.SetProgressBarState
import com.saqs.app.ui.explore.model.ExploreViewEvent
import com.saqs.app.ui.explore.model.ExploreViewEventType.Init
import com.saqs.app.ui.explore.model.ExploreViewEventType.NavigateEventItem
import com.saqs.app.ui.explore.model.ExploreViewState
import com.saqs.app.ui.explore.model._ExploreViewEffect
import com.saqs.app.ui.explore.model._ExploreViewState
import com.saqs.app.util.DateUtils
import com.saqs.app.util.Lce
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import timber.log.Timber

class ExploreViewModel @ViewModelInject constructor(
    private val eventsRepository: EventsRepository
) : ViewModel(), ExploreViewEvent {

    private val _state = _ExploreViewState()
    val state = ExploreViewState(_state)

    private val _effect = _ExploreViewEffect()
    val effect = ExploreViewEffect(_effect)

    fun attachEvents(fragment: ExploreFragment) {
        fragment.attachViewEvents(this)
    }

    override fun init(event: Init) {
        observeEventsRemote()
        observeEvents()
    }

    @VisibleForTesting
    internal fun observeEvents() {
        eventsRepository.getAll().onEach { lce ->
            when (lce) {
                is Lce.Loading -> {
                    _effect._setProgressBarState.emit(SetProgressBarState(View.VISIBLE))
                }
                is Lce.Error -> {
                    Timber.e(lce.error)
                }
                is Lce.Content -> {
                    _effect._setProgressBarState.emit(SetProgressBarState(View.INVISIBLE))
                    _state._events.value = lce.packet
                        .take(32)
                        .sortedBy { DateUtils.fromTimestamp(it.date) }
                }
            }
        }.launchIn(viewModelScope)
    }

    @VisibleForTesting
    internal fun observeEventsRemote() {
        viewModelScope.launch {
            eventsRepository.getAllRemote().collect()
        }
    }

    override fun navigateEventItem(event: NavigateEventItem) {
        viewModelScope.launch {
            _effect._purchaseTicket.emit(PurchaseTicketEffect(event.eventItem.id))
        }
    }
}
