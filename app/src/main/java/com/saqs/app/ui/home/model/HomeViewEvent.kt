/*
 * Copyright 2020 - André Thiele
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.ui.home.model

import com.saqs.app.domain.Event
import com.saqs.app.ui.home.model.HomeViewEventType.NavigateEventItem
import com.saqs.app.ui.home.model.HomeViewEventType.NavigateHello
import com.saqs.app.util.ViewEvent

interface HomeViewEvent : ViewEvent {
    fun navigateHello(event: NavigateHello)
    fun navigateEventItem(event: NavigateEventItem)
}

sealed class HomeViewEventType {
    object NavigateHello : HomeViewEventType()
    data class NavigateEventItem(val eventItem: Event) : HomeViewEventType()
}
