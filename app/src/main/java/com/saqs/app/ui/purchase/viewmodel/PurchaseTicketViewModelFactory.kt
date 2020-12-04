/*
 * Copyright 2020 - André Thiele
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.ui.purchase.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.saqs.app.data.EventRepository
import com.saqs.app.data.TicketRepository
import javax.inject.Inject

class PurchaseTicketViewModelFactory @Inject constructor(
    private val eventRepository: EventRepository,
    private val ticketRepository: TicketRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PurchaseTicketViewModel(
            eventRepository,
            ticketRepository
        ) as T
    }
}
