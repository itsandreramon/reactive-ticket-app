/*
 * Copyright 2020 - André Thiele
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.data

class EventRepositoryImpl private constructor(
    private val firebaseSource: FirebaseSource
) : EventRepository {

    companion object {

        @Volatile private var instance: EventRepositoryImpl? = null

        fun getInstance(
            firebaseSource: FirebaseSource
        ) = instance
            ?: EventRepositoryImpl(
                firebaseSource
            ).also { instance = it }
    }
}
