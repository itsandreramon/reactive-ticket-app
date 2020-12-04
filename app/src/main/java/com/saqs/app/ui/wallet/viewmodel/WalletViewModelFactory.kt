/*
 * Copyright 2020 - André Thiele
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.ui.wallet.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.saqs.app.data.EventRepository
import com.saqs.app.data.TicketRepository
import javax.inject.Inject

class WalletViewModelFactory @Inject constructor(
    private val eventRepository: EventRepository,
    private val ticketRepository: TicketRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return WalletViewModel(
            eventRepository,
            ticketRepository
        ) as T
    }
}
