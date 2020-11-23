/*
 * Copyright 2020 - André Thiele
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.ui.home.viewmodel

import androidx.lifecycle.ViewModel
import com.saqs.app.ui.home.HomeFragment
import com.saqs.app.ui.home.model.HomeViewEvent
import com.saqs.app.ui.home.model.HomeViewEventType.NavigateHello

class HomeViewModel : ViewModel(), HomeViewEvent {

    fun attachEvents(fragment: HomeFragment) {
        fragment.attachViewEvents(this)
    }

    override fun navigateHello(event: NavigateHello) {
        TODO("Not yet implemented")
    }
}
