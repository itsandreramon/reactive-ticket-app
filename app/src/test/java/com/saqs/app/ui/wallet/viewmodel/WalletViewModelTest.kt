/*
 * Copyright 2020 - André Thiele, Allan Fodi, Hüseyin Celik, Bertin Junior Wagueu Nkepgang
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.ui.wallet.viewmodel

import app.cash.turbine.test
import com.saqs.app.CoroutinesTestExtension
import com.saqs.app.data.events.EventsRepository
import com.saqs.app.data.tickets.TicketsRepository
import com.saqs.app.domain.Event
import com.saqs.app.domain.Ticket
import com.saqs.app.domain.TicketWithEvent
import com.saqs.app.util.Lce
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import kotlin.time.ExperimentalTime
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension

class WalletViewModelTest {

    private val eventsRepository = mockk<EventsRepository>()
    private val ticketsRepository = mockk<TicketsRepository>()

    private var walletViewModel: WalletViewModel? = null

    @JvmField
    @RegisterExtension
    val coroutinesTestExtension = CoroutinesTestExtension()

    @BeforeEach
    fun setupViewModel() {
        walletViewModel = WalletViewModel(eventsRepository, ticketsRepository)
    }

    @Nested
    inner class StateTests {

        @Test
        @ExperimentalTime
        fun `state events is set`() {
            coroutinesTestExtension.runBlockingTest {
                // Given
                val events = listOf(
                    Event(id = "1"),
                    Event(id = "2"),
                    Event(id = "3")
                )

                every {
                    eventsRepository.getAll()
                } returns flowOf(Lce.Content(events))

                // When
                walletViewModel!!.observeEvents()

                // Then
                walletViewModel!!.state.events.test {
                    expectItem() shouldBe events
                }
            }
        }

        @Test
        @ExperimentalTime
        fun `state tickets is set`() {
            coroutinesTestExtension.runBlockingTest {
                // Given
                val tickets = listOf(
                    Ticket(id = 1),
                    Ticket(id = 2),
                    Ticket(id = 3)
                )

                every {
                    ticketsRepository.getAll()
                } returns flowOf(Lce.Content(tickets))

                // When
                walletViewModel!!.observeTickets()

                // Then
                walletViewModel!!.state.tickets.test {
                    expectItem() shouldBe tickets
                }
            }
        }

        @Test
        @ExperimentalTime
        fun `state ticketsWithEvents is set`() {
            coroutinesTestExtension.runBlockingTest {
                // Given
                val tickets = listOf(
                    Ticket(id = 1, eventId = "1"),
                    Ticket(id = 2, eventId = "2"),
                    Ticket(id = 3, eventId = "3")
                )

                val events = listOf(
                    Event(id = "1"),
                    Event(id = "2"),
                    Event(id = "3")
                )

                val ticketsWithEvents = listOf(
                    TicketWithEvent(tickets[0], events[0], 1),
                    TicketWithEvent(tickets[1], events[1], 1),
                    TicketWithEvent(tickets[2], events[2], 1)
                )

                every {
                    ticketsRepository.getAll()
                } returns flowOf(Lce.Content(tickets))

                every {
                    eventsRepository.getAll()
                } returns flowOf(Lce.Content(events))

                // When
                walletViewModel!!.observeTickets()
                walletViewModel!!.observeEvents()
                walletViewModel!!.observeTicketsWithEvents()

                // Then
                walletViewModel!!.state.ticketsWithEvents.test {
                    expectItem() shouldBe ticketsWithEvents
                }
            }
        }
    }
}
