/*
 * Copyright 2020 - André Thiele
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.ui.wallet.model

import com.saqs.app.domain.Event
import com.saqs.app.ui.explore.model.HomeViewEventType.NavigateEventItem
import com.saqs.app.util.ViewEvent

interface WalletViewEvent : ViewEvent {
    fun navigateTicketItem(event: NavigateEventItem)
}

sealed class WalletViewEventType {
    data class NavigateTicketItem(val eventItem: Event) : WalletViewEventType()
}
