/*
 * Copyright 2020 - André Thiele
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.data

import com.google.firebase.firestore.FirebaseFirestore
import com.saqs.app.domain.Event
import kotlinx.coroutines.flow.Flow

interface FirebaseSource {
    val firebaseStorage: FirebaseFirestore

    fun observeEvents() : Flow<Event>
}
