/*
 * Copyright 2020 - André Thiele
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.ui.purchase.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saqs.app.data.CoroutinesDispatcherProvider
import com.saqs.app.data.EventRepository
import com.saqs.app.ui.explore.model.HomeViewEventType.NavigateEventItem
import com.saqs.app.ui.purchase.PurchaseTicketActivity
import com.saqs.app.ui.purchase.model.PurchaseTicketViewEffect
import com.saqs.app.ui.purchase.model.PurchaseTicketViewEffectType.NavigateExploreEffect
import com.saqs.app.ui.purchase.model.PurchaseTicketViewEvent
import com.saqs.app.ui.purchase.model.PurchaseTicketViewEventType.InitState
import com.saqs.app.ui.purchase.model.PurchaseTicketViewState
import com.saqs.app.ui.purchase.model._PurchaseTicketViewEffect
import com.saqs.app.ui.purchase.model._PurchaseTicketViewState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class PurchaseTicketViewModel(
    private val eventRepository: EventRepository,
    private val dispatcherProvider: CoroutinesDispatcherProvider
) : ViewModel(), PurchaseTicketViewEvent {

    private val _state = _PurchaseTicketViewState()
    val state = PurchaseTicketViewState(_state)

    private val _effect = _PurchaseTicketViewEffect()
    val effect = PurchaseTicketViewEffect(_effect)

    fun attachEvents(fragment: PurchaseTicketActivity) {
        fragment.attachViewEvents(this)
    }

    override fun initState(event: InitState) {
        eventRepository.inMemoryDatabase.onEach { eventList ->
            _state._selectedEvent.value = eventList.firstOrNull { it.id == event.eventId }
        }.launchIn(viewModelScope)
    }

    override fun buyTicket(event: NavigateEventItem) {
        viewModelScope.launch {
            _effect._navigateExplore.emit(NavigateExploreEffect)
        }
    }
}
