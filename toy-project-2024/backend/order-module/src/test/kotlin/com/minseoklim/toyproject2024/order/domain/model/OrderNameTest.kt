package com.minseoklim.toyproject2024.order.domain.model

import com.minseoklim.toyproject2024.test.util.TestUtil
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.assertj.core.api.Assertions.assertThatNoException
import org.junit.jupiter.api.Test

class OrderNameTest {

    @Test
    fun constructor() {
        // when, then
        assertThatNoException().isThrownBy {
            OrderName("주문명")
        }

        // when, then
        assertThatIllegalArgumentException().isThrownBy {
            OrderName("")
        }
    }

    @Test
    fun equalsAndHashCode() {
        // given
        val orderName1 = OrderName("주문명")
        val orderName2 = OrderName("주문명")
        val orderName3 = OrderName("다른 주문명")

        // when, then
        TestUtil.testEqualsAndHashCode(orderName1, orderName2, orderName3)
    }
}
