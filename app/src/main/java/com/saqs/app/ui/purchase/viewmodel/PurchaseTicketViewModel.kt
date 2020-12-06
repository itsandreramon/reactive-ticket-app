/*
 * Copyright 2020 - André Thiele
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.ui.purchase.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saqs.app.data.EventRepository
import com.saqs.app.data.TicketRepository
import com.saqs.app.domain.Ticket
import com.saqs.app.ui.purchase.PurchaseTicketActivity
import com.saqs.app.ui.purchase.model.PurchaseTicketViewEffect
import com.saqs.app.ui.purchase.model.PurchaseTicketViewEffectType.SetPurchaseButtonState
import com.saqs.app.ui.purchase.model.PurchaseTicketViewEffectType.ShowErrorDialogEffect
import com.saqs.app.ui.purchase.model.PurchaseTicketViewEvent
import com.saqs.app.ui.purchase.model.PurchaseTicketViewEventType.BuyTicket
import com.saqs.app.ui.purchase.model.PurchaseTicketViewEventType.InitState
import com.saqs.app.ui.purchase.model.PurchaseTicketViewState
import com.saqs.app.ui.purchase.model._PurchaseTicketViewEffect
import com.saqs.app.ui.purchase.model._PurchaseTicketViewState
import com.saqs.app.util.Result
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class PurchaseTicketViewModel @ViewModelInject constructor(
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

    init {
        state.selectedEvent.filterNotNull().onEach {
            // TODO
        }.launchIn(viewModelScope)
    }

    override fun initState(event: InitState) {
        eventRepository.getById(event.eventId).onEach {
            _state._selectedEvent.value = it
        }.launchIn(viewModelScope)
    }

    override fun buyTicket(event: BuyTicket) {
        viewModelScope.launch {
            _effect._setPurchaseButtonState.emit(SetPurchaseButtonState(false))
        }

        viewModelScope.launch {
            state.selectedEvent.value?.let { eventItem ->
                when (eventRepository.bookEventRemote(eventItem, event.amount)) {
                    is Result.Success -> {
                        repeat(event.amount) {
                            ticketRepository.addTicket(Ticket(eventId = eventItem.id))
                        }

                        _effect._setPurchaseButtonState.emit(SetPurchaseButtonState(true))
                    }
                    is Result.Error -> {
                        _effect._showErrorDialog.emit(ShowErrorDialogEffect)
                        _effect._setPurchaseButtonState.emit(SetPurchaseButtonState(true))
                    }
                }
            }
        }
    }
}
