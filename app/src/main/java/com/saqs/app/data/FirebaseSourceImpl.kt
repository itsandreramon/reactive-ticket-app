/*
 * Copyright 2020 - André Thiele
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.data

<<<<<<< HEAD
import com.google.firebase.firestore.DocumentReference
=======
import com.google.android.gms.tasks.Task
>>>>>>> 42c26509c8c8f19124152731e691a3eb9d3f28fe
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.saqs.app.domain.Event
<<<<<<< HEAD
import com.saqs.app.domain.Ticket
import com.saqs.app.util.FIRESTORE_COLLECTION_EVENTS
import com.saqs.app.util.FIRESTORE_COLLECTION_TICKETS
=======
import com.saqs.app.util.FIRESTORE_COLLECTION_EVENTS
>>>>>>> 42c26509c8c8f19124152731e691a3eb9d3f28fe
import com.saqs.app.util.Result
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import timber.log.Timber

class FirebaseSourceImpl : FirebaseSource {

    override val firestore: FirebaseFirestore by lazy { Firebase.firestore }

    override fun observeEvents(): Flow<Event> = callbackFlow {
        firestore.collection(FIRESTORE_COLLECTION_EVENTS)
            .addSnapshotListener { value, e ->
                if (e != null) {
                    return@addSnapshotListener
                }

                for (doc in value!!) {
                    try {
                        val event = doc
                            .toObject(Event::class.java)
                            .apply { id = doc.id }

                        offer(event)
                    } catch (e: Exception) {
                        Timber.e(e)
                    }
                }
            }

        awaitClose()
    }

<<<<<<< HEAD
    override fun observeTickets(): Flow<Ticket> = callbackFlow {
        firestore.collection(FIRESTORE_COLLECTION_TICKETS)
            .addSnapshotListener { value, e ->
                if (e != null) {
                    return@addSnapshotListener
                }

                for (doc in value!!) {
                    try {
                        val ticket = doc
                            .toObject(Ticket::class.java)
                            .apply { id = doc.id }

                        offer(ticket)
                    } catch (e: Exception) {
                        Timber.e(e)
                    }
                }
            }

        awaitClose()
    }

    override suspend fun updateEvent(event: Event): Result<Void> {
        return try {
            val res = firestore.collection(FIRESTORE_COLLECTION_EVENTS)
                .document(event.id)
                .set(event)
                .await()

            Result.Success(res)
        } catch (e: FirebaseFirestoreException) {
            Result.Error(e)
        }
    }

    override suspend fun addTicket(ticket: Ticket): Result<DocumentReference> {
        return try {
            val res = firestore.collection(FIRESTORE_COLLECTION_TICKETS)
                .add(ticket)
                .await()

            Result.Success(res)
=======
    override suspend fun bookEvent(event: Event, amount: Int): Result<Task<Double>> {
        return try {
            require(amount > 0)

            // Firestore Transaction
            val sfDocRef = firestore.collection(FIRESTORE_COLLECTION_EVENTS).document(event.id)
            val result = firestore.runTransaction { transaction ->
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
            }

            Result.Success(result)
>>>>>>> 42c26509c8c8f19124152731e691a3eb9d3f28fe
        } catch (e: FirebaseFirestoreException) {
            Result.Error(e)
        }
    }
}
