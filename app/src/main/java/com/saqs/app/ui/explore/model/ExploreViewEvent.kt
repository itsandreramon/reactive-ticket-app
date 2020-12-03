/*
 * Copyright 2020 - André Thiele
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.ui.explore.model

import com.saqs.app.domain.Event
import com.saqs.app.ui.explore.model.HomeViewEventType.NavigateEventItem
import com.saqs.app.util.ViewEvent

interface ExploreViewEvent : ViewEvent {
    fun navigateEventItem(event: NavigateEventItem)
}

sealed class HomeViewEventType {
    data class NavigateEventItem(val eventItem: Event) : HomeViewEventType()
}
