/*
 * Copyright 2020 - André Thiele
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.ui.purchase.viewmodel

import app.cash.turbine.test
import com.saqs.app.MainCoroutineRule
import com.saqs.app.data.events.EventsRepository
import com.saqs.app.data.tickets.TicketsRepository
import com.saqs.app.domain.Event
import com.saqs.app.domain.Ticket
import com.saqs.app.ui.purchase.model.PurchaseTicketViewEventType.BuyTicket
import com.saqs.app.ui.purchase.model.PurchaseTicketViewEventType.Init
import com.saqs.app.util.Result
import io.kotest.matchers.shouldBe
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import kotlin.time.ExperimentalTime
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PurchaseTicketViewModelTest {

    private val eventsRepository = mockk<EventsRepository>()
    private val ticketsRepository = mockk<TicketsRepository>()

    private lateinit var purchaseTicketViewModel: PurchaseTicketViewModel

    @get:Rule val coroutineRule = MainCoroutineRule()

    @Before
    fun setupViewModel() {
        purchaseTicketViewModel = PurchaseTicketViewModel(eventsRepository, ticketsRepository)
    }

    @Test
    @ExperimentalTime
    fun selectedItemStateIsSetCorrectly() = coroutineRule.runBlockingTest {
        // Given
        val event = Event(id = "2", amount = 10, available = 10)

        every {
            eventsRepository.getById("2")
        } returns flowOf(event)

        // When
        purchaseTicketViewModel.init(Init(eventId = "2"))

        // Then
        purchaseTicketViewModel.state.selectedEvent.test {
            expectItem() shouldBe event
        }
    }

    @Test
    @ExperimentalTime
    fun bookingAnEventSavesTicket() = coroutineRule.runBlockingTest {
        // Given
        val event = Event(id = "2", amount = 10, available = 10)
        val ticket = Ticket(eventId = "2")

        every {
            eventsRepository.getById("2")
        } returns flowOf(event)

        coEvery {
            eventsRepository.bookEventRemote(event, 1)
        } returns Result.Success((9.0))

        coEvery {
            ticketsRepository.insert(ticket)
        } just Runs

        // When
        purchaseTicketViewModel.init(Init(eventId = "2"))
        purchaseTicketViewModel.buyTicket(BuyTicket(1))

        // Then
        coVerify {
            ticketsRepository.insert(ticket)
        }
    }
}
