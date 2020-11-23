/*
 * Copyright 2020 - André Thiele
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.ui.home.model

import com.saqs.app.ui.home.model.HomeViewEffectType.ShowSnackBarEffect
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

class _HomeViewEffect(
    val _showSnackBar: MutableSharedFlow<ShowSnackBarEffect> = MutableSharedFlow()
)

class HomeViewEffect(_effect: _HomeViewEffect) {
    val showSnackBar: SharedFlow<ShowSnackBarEffect> = _effect._showSnackBar
}

sealed class HomeViewEffectType {
    object ShowSnackBarEffect : HomeViewEffectType()
}
