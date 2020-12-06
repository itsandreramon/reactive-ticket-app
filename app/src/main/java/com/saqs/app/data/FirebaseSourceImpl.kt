/*
 * Copyright 2020 - André Thiele
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.data

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.saqs.app.domain.Event
import com.saqs.app.util.FIRESTORE_COLLECTION_EVENTS
import com.saqs.app.util.Result
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import timber.log.Timber

class FirebaseSourceImpl : FirebaseSource {

    override val firestore: FirebaseFirestore by lazy { Firebase.firestore }

    override fun observeEvents(): Flow<List<Event>> = callbackFlow {
        firestore.collection(FIRESTORE_COLLECTION_EVENTS)
            .addSnapshotListener { value, e ->
                if (e != null) {
                    Timber.e(e)
                    return@addSnapshotListener
                }

                val buffer = mutableListOf<Event>()
                for (doc in value!!) {
                    try {
                        val event = doc
                            .toObject(Event::class.java)
                            .apply { id = doc.id }

                        buffer.add(event)
                    } catch (e: Exception) {
                        Timber.e(e)
                    }
                }

                // only emit once the whole data is fetched
                offer(buffer)
            }

        try {
            awaitClose()
        } catch (t: Throwable) {
            Timber.e(t) // called with JobCancellationException
        }
    }

    override suspend fun bookEvent(event: Event, amount: Int): Result<Double> {
        return try {
            require(amount > 0)

            // Firestore Transaction
            val sfDocRef = firestore.collection(FIRESTORE_COLLECTION_EVENTS).document(event.id)
            val result = firestore
                .runTransaction { transaction ->
                    val snapshot = transaction.get(sfDocRef)
                    val newAvailableTickets = snapshot.getDouble("available")!! - amount

                    if (newAvailableTickets >= 0) {
                        transaction.update(sfDocRef, "available", newAvailableTickets)
                        newAvailableTickets
                    } else {
                        throw FirebaseFirestoreException(
                            "Available tickets cannot be less than 0",
                            FirebaseFirestoreException.Code.ABORTED
                        )
                    }
                }.await()

            Result.Success(result)
        } catch (e: FirebaseFirestoreException) {
            Result.Error(e)
        }
    }
}
