/*
 * Copyright 2020 - André Thiele
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.ui.purchase.model

import com.saqs.app.ui.purchase.model.PurchaseTicketViewEffectType.NavigateExploreEffect
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

class _PurchaseTicketViewEffect(
    val _navigateExplore: MutableSharedFlow<NavigateExploreEffect> = MutableSharedFlow()
)

class PurchaseTicketViewEffect(_effect: _PurchaseTicketViewEffect) {
    val navigateExplore: SharedFlow<NavigateExploreEffect> = _effect._navigateExplore
}

sealed class PurchaseTicketViewEffectType {
    object NavigateExploreEffect : PurchaseTicketViewEffectType()
}
