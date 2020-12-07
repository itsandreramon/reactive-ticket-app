/*
 * Copyright 2020 - André Thiele
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.util

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class MathTest : FunSpec({

    test("round") {
        val given = 3.14159265359
        val expected = 3.14

        val actual = given.round(2)

        actual shouldBe expected
    }

    test("roundFraction") {
        val given = 0.711245
        val expected = 0.71

        val actual = given.round(2)

        actual shouldBe expected
    }

    test("roundNegative") {
        val given = -3.14159265359
        val expected = -3.14

        val actual = given.round(2)

        actual shouldBe expected
    }
})
