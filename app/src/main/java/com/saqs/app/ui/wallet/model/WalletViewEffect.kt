/*
 * Copyright 2020 - André Thiele
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.ui.wallet.model

import com.saqs.app.ui.wallet.model.WalletViewEffectType.ShowSnackBarEffect
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

class _WalletViewEffect(
    val _showSnackBar: MutableSharedFlow<ShowSnackBarEffect> = MutableSharedFlow()
)

class WalletViewEffect(_effect: _WalletViewEffect) {
    val showSnackBar: SharedFlow<ShowSnackBarEffect> = _effect._showSnackBar
}

sealed class WalletViewEffectType {
    object ShowSnackBarEffect : WalletViewEffectType()
}
