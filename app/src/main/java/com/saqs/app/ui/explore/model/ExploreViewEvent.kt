/*
 * Copyright 2020 - André Thiele, Allan Fodi, Hüseyin Celik, Bertin Junior Wagueu Nkepgang
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.ui.explore.model

import com.saqs.app.domain.Event
import com.saqs.app.ui.explore.model.ExploreViewEventType.NavigateEventItem
import com.saqs.app.util.ViewEvent

interface ExploreViewEvent : ViewEvent {
    fun navigateEventItem(event: NavigateEventItem)
}

sealed class ExploreViewEventType {
    data class NavigateEventItem(val eventItem: Event) : ExploreViewEventType()
}
