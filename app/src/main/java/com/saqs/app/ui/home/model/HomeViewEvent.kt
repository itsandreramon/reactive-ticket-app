/*
 * Copyright 2020 - André Thiele
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.ui.home.model

import com.saqs.app.ui.home.model.HomeViewEventType.NavigateHello
import com.saqs.app.util.ViewEvent

interface HomeViewEvent : ViewEvent {
    fun navigateHello(event: NavigateHello)
}

sealed class HomeViewEventType {
    object NavigateHello : HomeViewEventType()
}
