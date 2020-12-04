/*
 * Copyright 2020 - André Thiele
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.ui.purchase.model

import com.saqs.app.ui.explore.model.HomeViewEventType.NavigateEventItem
import com.saqs.app.util.ViewEvent

interface PurchaseTicketViewEvent : ViewEvent {
    fun buyTicket(event: NavigateEventItem)
}

sealed class PurchaseTicketViewEventType {
    data class BuyTicket(val eventId: String, val amount: Int) : PurchaseTicketViewEventType()
}
