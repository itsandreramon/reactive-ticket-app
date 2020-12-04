/*
 * Copyright 2020 - André Thiele
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.ui.explore.model

import com.saqs.app.ui.explore.model.ExploreViewEffectType.PurchaseTicketEffect
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

class _ExploreViewEffect(
    val _purchaseTicket: MutableSharedFlow<PurchaseTicketEffect> = MutableSharedFlow()
)

class ExploreViewEffect(_effect: _ExploreViewEffect) {
    val purchaseTicket: SharedFlow<PurchaseTicketEffect> = _effect._purchaseTicket
}

sealed class ExploreViewEffectType {
    data class PurchaseTicketEffect(val eventId: String) : ExploreViewEffectType()
}
