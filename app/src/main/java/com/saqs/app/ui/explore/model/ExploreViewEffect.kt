/*
 * Copyright 2020 - André Thiele, Allan Fodi, Hüseyin Celik, Bertin Junior Wagueu Nkepgang
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.ui.explore.model

import com.saqs.app.ui.explore.model.ExploreViewEffectType.PurchaseTicketEffect
import com.saqs.app.ui.explore.model.ExploreViewEffectType.SetProgressBarState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

class _ExploreViewEffect(
    val _purchaseTicket: MutableSharedFlow<PurchaseTicketEffect> = MutableSharedFlow(),
    val _setProgressBarState: MutableSharedFlow<SetProgressBarState> = MutableSharedFlow()
)

class ExploreViewEffect(_effect: _ExploreViewEffect) {
    val purchaseTicket: SharedFlow<PurchaseTicketEffect> = _effect._purchaseTicket
    val setProgressBarState: SharedFlow<SetProgressBarState> = _effect._setProgressBarState
}

sealed class ExploreViewEffectType {
    data class PurchaseTicketEffect(val eventId: String) : ExploreViewEffectType()
    data class SetProgressBarState(val visibility: Int) : ExploreViewEffectType()
}
