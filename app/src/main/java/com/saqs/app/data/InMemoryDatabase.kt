/*
 * Copyright 2020 - André Thiele
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.data

import com.saqs.app.domain.Ticket
import kotlinx.coroutines.flow.MutableStateFlow

object InMemoryDatabase : DataSource {
    override val tickets: MutableStateFlow<List<Ticket>> = MutableStateFlow(listOf())
}
