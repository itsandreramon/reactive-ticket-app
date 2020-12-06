/*
 * Copyright 2020 - André Thiele
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.ui.purchase.viewmodel

import android.view.View
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saqs.app.data.EventRepository
import com.saqs.app.data.TicketRepository
import com.saqs.app.domain.Ticket
import com.saqs.app.ui.purchase.PurchaseTicketActivity
import com.saqs.app.ui.purchase.model.PurchaseTicketViewEffect
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
            _state._layoutAmountVisible.value = if (it.available > 0) {
                View.VISIBLE
            } else {
                View.INVISIBLE
            }
        }.launchIn(viewModelScope)
    }

    override fun initState(event: InitState) {
        eventRepository.getById(event.eventId).onEach {
            _state._selectedEvent.value = it
        }.launchIn(viewModelScope)
    }

    override fun buyTicket(event: BuyTicket) {
        viewModelScope.launch {
            _state._dialogLoadingVisible.value = true
            _state._buttonPurchaseEnabled.value = false
        }

        viewModelScope.launch {
            state.selectedEvent.value?.let { eventItem ->
                when (eventRepository.bookEventRemote(eventItem, event.amount)) {
                    is Result.Success -> {
                        repeat(event.amount) {
                            ticketRepository.addTicket(Ticket(eventId = eventItem.id))
                        }
                        _state._dialogLoadingVisible.value = false
                        _state._buttonPurchaseEnabled.value = true
                    }
                    is Result.Error -> {
                        _state._dialogLoadingVisible.value = false
                        _state._buttonPurchaseEnabled.value = true
                        _effect._showErrorDialog.emit(ShowErrorDialogEffect)
                    }
                }
            }
        }
    }
}
