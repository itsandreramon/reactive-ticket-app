/*
 * Copyright 2020 - André Thiele
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.data

import com.google.firebase.firestore.FirebaseFirestore
import com.saqs.app.domain.Event
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import timber.log.Timber

class FirebaseSourceImpl : FirebaseSource {

    override val firebaseStorage: FirebaseFirestore by lazy { FirebaseFirestore.getInstance() }

    override fun observeEvents(): Flow<Event> = callbackFlow {
        firebaseStorage.collection("events")
            .addSnapshotListener { value, e ->
                if (e != null) {
                    return@addSnapshotListener
                }

                for (doc in value!!) {
                    try {
                        val event = doc.toObject(Event::class.java).apply {
                            id = doc.id
                        }
                        offer(event)
                    } catch (e: Exception) {
                        Timber.e(e)
                    }
                }
            }

        awaitClose()
    }
}
