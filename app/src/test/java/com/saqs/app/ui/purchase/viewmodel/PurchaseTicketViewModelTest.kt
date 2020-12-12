/*
 * Copyright 2020 - André Thiele, Allan Fodi, Hüseyin Celik, Bertin Junior Wagueu Nkepgang
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.ui.purchase.viewmodel

import app.cash.turbine.test
import com.saqs.app.CoroutinesTestExtension
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
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension

class PurchaseTicketViewModelTest {

    private val eventsRepository = mockk<EventsRepository>()
    private val ticketsRepository = mockk<TicketsRepository>()

    private var purchaseTicketViewModel: PurchaseTicketViewModel? = null

    @JvmField
    @RegisterExtension
    val coroutinesTestExtension = CoroutinesTestExtension()

    @BeforeEach
    fun setupViewModel() {
        purchaseTicketViewModel = PurchaseTicketViewModel(eventsRepository, ticketsRepository)
    }

    @Nested
    inner class StateTests {

        @Test
        @ExperimentalTime
        fun `state selectedItem is set`() = coroutinesTestExtension.runBlockingTest {
            // Given
            val event = Event(id = "2", amount = 10, available = 10)

            every {
                eventsRepository.getById("2")
            } returns flowOf(event)

            // When
            purchaseTicketViewModel!!.init(Init(eventId = "2"))

            // Then
            purchaseTicketViewModel!!.state.selectedEvent.test {
                expectItem() shouldBe event
            }
        }
    }

    @Nested
    inner class EventTests {

        @Test
        @ExperimentalTime
        fun `event bookEvent inserts ticket`() = coroutinesTestExtension.runBlockingTest {
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
            purchaseTicketViewModel!!.init(Init(eventId = "2"))
            purchaseTicketViewModel!!.buyTicket(BuyTicket(1))

            // Then
            coVerify {
                ticketsRepository.insert(ticket)
            }
        }
    }
}
