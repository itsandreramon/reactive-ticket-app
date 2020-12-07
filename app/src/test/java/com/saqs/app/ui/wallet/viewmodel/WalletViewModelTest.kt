package com.saqs.app.ui.wallet.viewmodel

import app.cash.turbine.test
import com.saqs.app.MainCoroutineRule
import com.saqs.app.data.events.EventsRepository
import com.saqs.app.data.tickets.TicketsRepository
import com.saqs.app.domain.Event
import com.saqs.app.domain.Ticket
import com.saqs.app.domain.TicketWithEvent
import com.saqs.app.util.Lce
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.time.ExperimentalTime

class WalletViewModelTest {

    private val eventsRepository = mockk<EventsRepository>()
    private val ticketsRepository = mockk<TicketsRepository>()

    private lateinit var walletViewModel: WalletViewModel

    @get:Rule val coroutineRule = MainCoroutineRule()

    @Before
    fun setupViewModel() {
        walletViewModel = WalletViewModel(eventsRepository, ticketsRepository)
    }

    @Test
    @ExperimentalTime
    fun eventsStateIsSetCorrectly() = coroutineRule.runBlockingTest {
        // Given
        val events = listOf(
            Event(id = "1"),
            Event(id = "2"),
            Event(id = "3"),
        )

        every {
            eventsRepository.getAll()
        } returns flowOf(Lce.Content(events))

        // When
        walletViewModel.observeEvents()

        // Then
        walletViewModel.state.events.test {
            expectItem() shouldBe events
        }
    }

    @Test
    @ExperimentalTime
    fun ticketsStateIsSetCorrectly() = coroutineRule.runBlockingTest {
        // Given
        val tickets = listOf(
            Ticket(id = 1),
            Ticket(id = 2),
            Ticket(id = 3),
        )

        every {
            ticketsRepository.getAll()
        } returns flowOf(Lce.Content(tickets))

        // When
        walletViewModel.observeTickets()

        // Then
        walletViewModel.state.tickets.test {
            expectItem() shouldBe tickets
        }
    }

    @Test
    @ExperimentalTime
    fun ticketsAndEventsStateIsSetCorrectly() = coroutineRule.runBlockingTest {
        // Given
        val tickets = listOf(
            Ticket(id = 1, eventId = "1"),
            Ticket(id = 2, eventId = "2"),
            Ticket(id = 3, eventId = "3"),
        )

        val events = listOf(
            Event(id = "1"),
            Event(id = "2"),
            Event(id = "3"),
        )

        val ticketsWithEvents = listOf(
            TicketWithEvent(tickets[0], events[0], 1),
            TicketWithEvent(tickets[1], events[1], 1),
            TicketWithEvent(tickets[2], events[2], 1),
        )

        every {
            ticketsRepository.getAll()
        } returns flowOf(Lce.Content(tickets))

        every {
            eventsRepository.getAll()
        } returns flowOf(Lce.Content(events))

        // When
        walletViewModel.observeTickets()
        walletViewModel.observeEvents()
        walletViewModel.observeTicketsWithEvents()

        // Then
        walletViewModel.state.ticketsWithEvents.test {
            expectItem() shouldBe ticketsWithEvents
        }
    }
}