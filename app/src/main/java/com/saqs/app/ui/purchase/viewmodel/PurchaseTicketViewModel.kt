/*
 * Copyright 2020 - André Thiele
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.ui.purchase.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saqs.app.data.EventRepository
import com.saqs.app.data.TicketRepository
import com.saqs.app.domain.Ticket
import com.saqs.app.ui.purchase.PurchaseTicketActivity
import com.saqs.app.ui.purchase.model.PurchaseTicketViewEffect
import com.saqs.app.ui.purchase.model.PurchaseTicketViewEffectType.NavigateExploreEffect
import com.saqs.app.ui.purchase.model.PurchaseTicketViewEffectType.ShowErrorDialogEffect
import com.saqs.app.ui.purchase.model.PurchaseTicketViewEvent
import com.saqs.app.ui.purchase.model.PurchaseTicketViewEventType.BuyTicket
import com.saqs.app.ui.purchase.model.PurchaseTicketViewEventType.InitState
import com.saqs.app.ui.purchase.model.PurchaseTicketViewState
import com.saqs.app.ui.purchase.model._PurchaseTicketViewEffect
import com.saqs.app.ui.purchase.model._PurchaseTicketViewState
import com.saqs.app.util.Lce
import com.saqs.app.util.Result
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class PurchaseTicketViewModel(
    private val eventRepository: EventRepository,
    private val ticketRepository: TicketRepository
) : ViewModel(), PurchaseTicketViewEvent {

    private val _state = _PurchaseTicketViewState()
    val state = PurchaseTicketViewState(_state)

    private val _effect = _PurchaseTicketViewEffect()
    val effect = PurchaseTicketViewEffect(_effect)

    fun attachEvents(fragment: PurchaseTicketActivity) {
        fragment.attachViewEvents(this)
    }

    override fun initState(event: InitState) {
        eventRepository.getAll().onEach { eventsLce ->
            when (eventsLce) {
                is Lce.Loading -> {
                    // TODO
                }
                is Lce.Error -> {
                    // TODO
                }
                is Lce.Content -> {
                    val events = eventsLce.packet
                    _state._selectedEvent.value = events.firstOrNull { it.id == event.eventId }
                }
            }
        }.launchIn(viewModelScope)
    }

    override fun buyTicket(event: BuyTicket) {
        viewModelScope.launch {
            repeat(event.amount) {
                state.selectedEvent.value?.let { eventItem ->
                    when (ticketRepository.addTicketRemote(Ticket(eventId = eventItem.id))) {
                        is Result.Success -> {
                            val updatedEvent = eventItem.copy(amount = eventItem.amount - 1)
                            eventRepository.updateEventRemote(updatedEvent)
                        }
                        is Result.Error -> {
                            _effect._showErrorDialog.emit(ShowErrorDialogEffect)
                        }
                    }
                }
            }

            _effect._navigateExplore.emit(NavigateExploreEffect)
        }
    }
}
