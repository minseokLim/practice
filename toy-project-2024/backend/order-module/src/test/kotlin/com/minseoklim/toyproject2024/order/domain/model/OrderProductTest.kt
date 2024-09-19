package com.minseoklim.toyproject2024.order.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.assertj.core.api.Assertions.assertThatNoException
import org.junit.jupiter.api.Test

class OrderProductTest {
    @Test
    fun constructor() {
        // when, then
        assertThatNoException().isThrownBy {
            OrderProduct(1, 1)
        }

        // when, then
        assertThatIllegalArgumentException().isThrownBy {
            OrderProduct(1, 0)
        }
    }

    @Test
    fun equalsAndHashCode() {
        // given
        val orderProduct1 = OrderProduct(1, 1)
        val orderProduct2 = OrderProduct(1, 1)
        val orderProduct3 = OrderProduct(2, 1)

        // when, then
        assertThat(setOf(orderProduct1, orderProduct2, orderProduct3)).hasSize(2)
    }
}
