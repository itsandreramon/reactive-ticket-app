/*
 * Copyright 2020 - André Thiele
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.ui.explore.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saqs.app.data.EventRepository
import com.saqs.app.ui.explore.ExploreFragment
import com.saqs.app.ui.explore.model.ExploreViewEffect
import com.saqs.app.ui.explore.model.ExploreViewEffectType.PurchaseTicketEffect
import com.saqs.app.ui.explore.model.ExploreViewEvent
import com.saqs.app.ui.explore.model.ExploreViewEventType.NavigateEventItem
import com.saqs.app.ui.explore.model.ExploreViewState
import com.saqs.app.ui.explore.model._ExploreViewEffect
import com.saqs.app.ui.explore.model._ExploreViewState
<<<<<<< HEAD
=======
import com.saqs.app.util.DateUtils
>>>>>>> 42c26509c8c8f19124152731e691a3eb9d3f28fe
import com.saqs.app.util.Lce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class ExploreViewModel @ViewModelInject constructor(
    private val eventRepository: EventRepository
) : ViewModel(), ExploreViewEvent {

    private val _state = _ExploreViewState()
    val state = ExploreViewState(_state)

    private val _effect = _ExploreViewEffect()
    val effect = ExploreViewEffect(_effect)

    init {
        eventRepository.observeEventsRemote().onEach {
            eventRepository.addEvent(it)
        }.launchIn(viewModelScope)

<<<<<<< HEAD
        eventRepository.getAll().take(32).onEach { lce ->
=======
        eventRepository.getAll().onEach { lce ->
>>>>>>> 42c26509c8c8f19124152731e691a3eb9d3f28fe
            when (lce) {
                is Lce.Loading -> {
                    // TODO
                }
                is Lce.Error -> {
                    // TODO
                }
                is Lce.Content -> {
                    _state._events.value = lce.packet
<<<<<<< HEAD
=======
                        .take(32)
                        .sortedBy { DateUtils.fromTimestamp(it.date) }
>>>>>>> 42c26509c8c8f19124152731e691a3eb9d3f28fe
                }
            }
        }.launchIn(viewModelScope)
    }

    fun attachEvents(fragment: ExploreFragment) {
        fragment.attachViewEvents(this)
    }

    override fun navigateEventItem(event: NavigateEventItem) {
        viewModelScope.launch {
            _effect._purchaseTicket.emit(PurchaseTicketEffect(event.eventItem.id))
        }
    }
}
