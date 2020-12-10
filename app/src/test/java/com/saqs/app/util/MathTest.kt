/*
 * Copyright 2020 - André Thiele
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.util

import io.kotest.matchers.shouldBe
import org.junit.Test

class MathTest {

    @Test
    fun round() {
        val given = 3.14159265359
        val expected = 3.14

        val actual = given.round(2)

        actual shouldBe expected
    }

    @Test
    fun roundFraction() {
        val given = 0.711245
        val expected = 0.71

        val actual = given.round(2)

        actual shouldBe expected
    }

    @Test
    fun roundNegative() {
        val given = -3.14159265359
        val expected = -3.14

        val actual = given.round(2)

        actual shouldBe expected
    }
}
