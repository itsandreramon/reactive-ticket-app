/*
 * Copyright 2020 - André Thiele
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.util

import kotlin.math.pow

fun Double.round(decimalPlaces: Int = 2): Double {
    require(decimalPlaces >= 0)
    val times = 10.toDouble().pow(decimalPlaces).toInt()
    return kotlin.math.round(this * times) / times
}
