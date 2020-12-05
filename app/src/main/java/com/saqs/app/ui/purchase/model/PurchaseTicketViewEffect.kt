/*
 * Copyright 2020 - André Thiele
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.ui.purchase.model

import com.saqs.app.ui.purchase.model.PurchaseTicketViewEffectType.NavigateExploreEffect
<<<<<<< HEAD
=======
import com.saqs.app.ui.purchase.model.PurchaseTicketViewEffectType.SetPurchaseButtonState
>>>>>>> 42c26509c8c8f19124152731e691a3eb9d3f28fe
import com.saqs.app.ui.purchase.model.PurchaseTicketViewEffectType.ShowErrorDialogEffect
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

class _PurchaseTicketViewEffect(
    val _navigateExplore: MutableSharedFlow<NavigateExploreEffect> = MutableSharedFlow(),
<<<<<<< HEAD
    val _showErrorDialog: MutableSharedFlow<ShowErrorDialogEffect> = MutableSharedFlow()
=======
    val _showErrorDialog: MutableSharedFlow<ShowErrorDialogEffect> = MutableSharedFlow(),
    val _setPurchaseButtonState: MutableSharedFlow<SetPurchaseButtonState> = MutableSharedFlow()
>>>>>>> 42c26509c8c8f19124152731e691a3eb9d3f28fe
)

class PurchaseTicketViewEffect(_effect: _PurchaseTicketViewEffect) {
    val navigateExplore: SharedFlow<NavigateExploreEffect> = _effect._navigateExplore
    val showErrorDialog: SharedFlow<ShowErrorDialogEffect> = _effect._showErrorDialog
<<<<<<< HEAD
=======
    val setPurchaseButtonState: SharedFlow<SetPurchaseButtonState> = _effect._setPurchaseButtonState
>>>>>>> 42c26509c8c8f19124152731e691a3eb9d3f28fe
}

sealed class PurchaseTicketViewEffectType {
    object NavigateExploreEffect : PurchaseTicketViewEffectType()
    object ShowErrorDialogEffect : PurchaseTicketViewEffectType()
<<<<<<< HEAD
=======
    data class SetPurchaseButtonState(val enabled: Boolean) : PurchaseTicketViewEffectType()
>>>>>>> 42c26509c8c8f19124152731e691a3eb9d3f28fe
}
