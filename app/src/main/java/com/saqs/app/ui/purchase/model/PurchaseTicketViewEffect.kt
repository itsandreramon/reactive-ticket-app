/*
 * Copyright 2020 - André Thiele
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.ui.purchase.model

import com.saqs.app.ui.purchase.model.PurchaseTicketViewEffectType.NavigateExploreEffect
import com.saqs.app.ui.purchase.model.PurchaseTicketViewEffectType.SetPurchaseButtonState
import com.saqs.app.ui.purchase.model.PurchaseTicketViewEffectType.ShowErrorDialogEffect
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

class _PurchaseTicketViewEffect(
    val _navigateExplore: MutableSharedFlow<NavigateExploreEffect> = MutableSharedFlow(),
    val _showErrorDialog: MutableSharedFlow<ShowErrorDialogEffect> = MutableSharedFlow(),
    val _setPurchaseButtonState: MutableSharedFlow<SetPurchaseButtonState> = MutableSharedFlow()
)

class PurchaseTicketViewEffect(_effect: _PurchaseTicketViewEffect) {
    val navigateExplore: SharedFlow<NavigateExploreEffect> = _effect._navigateExplore
    val showErrorDialog: SharedFlow<ShowErrorDialogEffect> = _effect._showErrorDialog
    val setPurchaseButtonState: SharedFlow<SetPurchaseButtonState> = _effect._setPurchaseButtonState
}

sealed class PurchaseTicketViewEffectType {
    object NavigateExploreEffect : PurchaseTicketViewEffectType()
    object ShowErrorDialogEffect : PurchaseTicketViewEffectType()
    data class SetPurchaseButtonState(val enabled: Boolean) : PurchaseTicketViewEffectType()
}
