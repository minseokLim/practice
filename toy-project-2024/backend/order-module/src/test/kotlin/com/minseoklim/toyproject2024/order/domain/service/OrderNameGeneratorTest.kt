package com.minseoklim.toyproject2024.order.domain.service

import com.minseoklim.toyproject2024.order.domain.model.Product
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class OrderNameGeneratorTest {
    @Test
    fun generate() {
        // when, then
        assertThatIllegalArgumentException().isThrownBy {
            OrderNameGenerator.generate(emptyList())
        }

        // given
        val products1 = listOf(Product(1, "상품1", 1000, 10, false))

        // when
        val result1 = OrderNameGenerator.generate(products1)

        // then
        assertEquals("상품1", result1)

        // given
        val products2 = listOf(
            Product(1, "상품1", 1000, 10, false),
            Product(2, "상품2", 2000, 20, false),
            Product(3, "상품3", 3000, 30, false)
        )

        val result2 = OrderNameGenerator.generate(products2)

        assertEquals("상품1 외 2종", result2)
    }
}
