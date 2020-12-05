/*
 * Copyright 2020 - André Thiele
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.data

<<<<<<< HEAD
import java.util.concurrent.Executors
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asCoroutineDispatcher
=======
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asCoroutineDispatcher
import java.util.concurrent.Executors
import javax.inject.Inject
>>>>>>> 42c26509c8c8f19124152731e691a3eb9d3f28fe

data class CoroutinesDispatcherProvider(
    val main: CoroutineDispatcher,
    val io: CoroutineDispatcher,
    val db: CoroutineDispatcher
) {
    @Inject
    constructor() : this(
        main = Dispatchers.Main,
        io = Dispatchers.IO,
        db = Executors.newSingleThreadExecutor().asCoroutineDispatcher()
    )
}
