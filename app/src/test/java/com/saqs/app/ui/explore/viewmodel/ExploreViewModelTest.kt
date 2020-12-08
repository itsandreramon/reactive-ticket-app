/*
 * Copyright 2020 - André Thiele, Allan Fodi, Hüseyin Celik, Bertin Junior Wagueu Nkepgang
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.ui.explore.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.saqs.app.MainCoroutineRule
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
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ExploreViewModelTest {

    private val eventsRepository = mockk<EventsRepository>()
    private lateinit var exploreViewModel: ExploreViewModel

    @get:Rule val coroutineRule = MainCoroutineRule()

    @Before
    fun setupViewModel() {
        exploreViewModel = ExploreViewModel(eventsRepository)
    }

    @Test
    @ExperimentalTime
    fun stateIsSetCorrectly() = coroutineRule.runBlockingTest {
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
        exploreViewModel.observeEvents()

        // Then
        exploreViewModel.state.events.test {
            expectItem() shouldBe expected
        }
    }

    @Test
    fun navigatePurchaseTicketEventEmitsNavigateEffect() = coroutineRule.runBlockingTest {
        val expected = PurchaseTicketEffect(eventId = "2")

        launch {
            exploreViewModel.effect.purchaseTicket.take(1).collect { effect ->
                effect shouldBe expected
                cancel() // would never complete
            }
        }

        exploreViewModel.navigateEventItem(NavigateEventItem(Event(id = "2")))
    }
}
