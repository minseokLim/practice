package com.minseoklim.toyproject2024.order.domain.service

import com.minseoklim.toyproject2024.order.domain.model.OrderProduct
import com.minseoklim.toyproject2024.order.domain.model.Product
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TotalAmountCalculatorTest {
    @Test
    fun calculate() {
        // given
        val orderProducts = listOf(
            OrderProduct(1, 1),
            OrderProduct(2, 2),
            OrderProduct(3, 3)
        )
        val products = listOf(
            Product(1, "상품1", 1000, 10, false),
            Product(2, "상품2", 2000, 20, false),
            Product(3, "상품3", 3000, 30, false)
        )

        // when
        val result = TotalAmountCalculator.calculate(orderProducts, products)

        // then
        assertEquals(14000, result)
    }
}
