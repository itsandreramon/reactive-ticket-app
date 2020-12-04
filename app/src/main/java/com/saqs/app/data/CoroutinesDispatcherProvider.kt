/*
 * Copyright 2020 - André Thiele
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.data

import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

data class CoroutinesDispatcherProvider(
    val main: CoroutineDispatcher,
    val io: CoroutineDispatcher
) {
    @Inject
    constructor() : this(
        main = Dispatchers.Main,
        io = Dispatchers.IO
    )
}
