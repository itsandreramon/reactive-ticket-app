/*
 * Copyright 2020 - André Thiele
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.ui.explore.model

import com.saqs.app.ui.explore.model.ExploreViewEffectType.ShowSnackBarEffect
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

class _ExploreViewEffect(
    val _showSnackBar: MutableSharedFlow<ShowSnackBarEffect> = MutableSharedFlow()
)

class ExploreViewEffect(_effect: _ExploreViewEffect) {
    val showSnackBar: SharedFlow<ShowSnackBarEffect> = _effect._showSnackBar
}

sealed class ExploreViewEffectType {
    object ShowSnackBarEffect : ExploreViewEffectType()
}
