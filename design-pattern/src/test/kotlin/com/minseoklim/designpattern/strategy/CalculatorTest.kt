package com.minseoklim.designpattern.strategy

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CalculatorTest {

    @Test
    fun calculate() {
        // given
        val items = listOf(
            Item("apple", 1000, true),
            Item("banana", 2000, false),
            Item("orange", 3000, true)
        )
        val calculator = Calculator(FirstGuestDiscountStrategy())

        // when
        val price = calculator.calculate(items)

        // then
        assertEquals(5400, price)
    }
}
