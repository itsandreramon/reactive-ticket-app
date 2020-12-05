/*
 * Copyright 2020 - André Thiele
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Ticket(
<<<<<<< HEAD
    var id: String = "-1",
=======
>>>>>>> 42c26509c8c8f19124152731e691a3eb9d3f28fe
    val eventId: String = "-1"
) : Parcelable
