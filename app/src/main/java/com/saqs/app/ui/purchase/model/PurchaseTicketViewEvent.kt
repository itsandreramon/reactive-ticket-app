/*
 * Copyright 2020 - André Thiele, Allan Fodi, Hüseyin Celik, Bertin Junior Wagueu Nkepgang
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.ui.purchase.model
import com.saqs.app.ui.purchase.model.PurchaseTicketViewEventType.BuyTicket
import com.saqs.app.ui.purchase.model.PurchaseTicketViewEventType.Init
import com.saqs.app.util.ViewEvent

interface PurchaseTicketViewEvent : ViewEvent {
    fun init(event: Init)
    fun buyTicket(event: BuyTicket)
}

sealed class PurchaseTicketViewEventType {
    data class Init(val eventId: String) : PurchaseTicketViewEventType()
    data class BuyTicket(val amount: Int) : PurchaseTicketViewEventType()
}
