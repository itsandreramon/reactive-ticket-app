/*
 * Copyright 2020 - André Thiele, Allan Fodi, Hüseyin Celik, Bertin Junior Wagueu Nkepgang
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.ui.explore.viewmodel

import app.cash.turbine.test
import com.saqs.app.CoroutinesTestExtension
import com.saqs.app.data.events.EventsRepository
import com.saqs.app.domain.Event
import com.saqs.app.ui.explore.model.ExploreViewEffectType.PurchaseTicketEffect
import com.saqs.app.ui.explore.model.ExploreViewEventType.NavigateEventItem
import com.saqs.app.util.Lce
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import kotlin.time.ExperimentalTime
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension

class ExploreViewModelTest {

    private val eventsRepository = mockk<EventsRepository>()
    private var exploreViewModel: ExploreViewModel? = null

    @JvmField
    @RegisterExtension
    val coroutinesTestExtension = CoroutinesTestExtension()

    @BeforeEach
    fun setupViewModel() {
        exploreViewModel = ExploreViewModel(eventsRepository)
    }

    @Test
    fun `event navigateEventItem emits purchaseTicketEffect`() = coroutinesTestExtension.runBlockingTest {
            // Given
            val expected = PurchaseTicketEffect(eventId = "2")

            // Then
            launch {
                exploreViewModel!!.effect.purchaseTicket.take(1).collect { effect ->
                    effect shouldBe expected
                    cancel() // cancel shared flow manually
                }
            }

            // When
            exploreViewModel!!.navigateEventItem(NavigateEventItem(Event(id = "2")))
        }

    @Test
    @ExperimentalTime
    fun `state events are set`() = coroutinesTestExtension.runBlockingTest {
        // Given
        val expected = listOf(
            Event(id = "1", amount = 10, available = 10),
            Event(id = "2", amount = 20, available = 20),
            Event(id = "3", amount = 30, available = 30)
        )

        every {
            eventsRepository.getAll()
        } returns flowOf(Lce.Content(expected))

        // When
        exploreViewModel!!.observeEvents()

        // Then
        exploreViewModel!!.state.events.test {
            expectItem() shouldBe expected
        }
    }
}
