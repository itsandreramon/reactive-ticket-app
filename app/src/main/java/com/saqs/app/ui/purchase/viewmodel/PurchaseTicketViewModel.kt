/*
 * Copyright 2020 - André Thiele
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.ui.purchase.viewmodel

import androidx.lifecycle.ViewModel
import com.saqs.app.data.CoroutinesDispatcherProvider
import com.saqs.app.ui.explore.model.HomeViewEventType
import com.saqs.app.ui.purchase.PurchaseTicketActivity
import com.saqs.app.ui.purchase.model.PurchaseTicketViewEffect
import com.saqs.app.ui.purchase.model.PurchaseTicketViewEvent
import com.saqs.app.ui.purchase.model.PurchaseTicketViewState
import com.saqs.app.ui.purchase.model._PurchaseTicketViewEffect
import com.saqs.app.ui.purchase.model._PurchaseTicketViewState

class PurchaseTicketViewModel(
    private val dispatcherProvider: CoroutinesDispatcherProvider
) : ViewModel(), PurchaseTicketViewEvent {

    private val _state = _PurchaseTicketViewState()
    val state = PurchaseTicketViewState(_state)

    private val _effect = _PurchaseTicketViewEffect()
    val effect = PurchaseTicketViewEffect(_effect)

    fun attachEvents(fragment: PurchaseTicketActivity) {
        fragment.attachViewEvents(this)
    }

    override fun buyTicket(event: HomeViewEventType.NavigateEventItem) {
    }
}
