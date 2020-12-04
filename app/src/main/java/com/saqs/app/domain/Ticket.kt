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
class Ticket(
    val eventId: String = "-1",
    val amount: Int = 1
) : Parcelable
