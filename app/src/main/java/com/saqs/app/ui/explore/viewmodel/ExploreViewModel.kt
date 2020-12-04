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
        eventRepository.getAllRemote().onEach {
            eventRepository.addEvent(it)
        }.launchIn(viewModelScope)

        eventRepository.getAll().onEach {
            _state._events.value = it
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
