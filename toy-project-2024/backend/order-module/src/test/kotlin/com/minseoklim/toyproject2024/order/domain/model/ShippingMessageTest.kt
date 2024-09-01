package com.minseoklim.toyproject2024.order.domain.model

import com.minseoklim.toyproject2024.test.util.TestUtil
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.assertj.core.api.Assertions.assertThatNoException
import org.junit.jupiter.api.Test

class ShippingMessageTest {

    @Test
    fun constructor() {
        // when, then
        assertThatNoException().isThrownBy {
            ShippingMessage("배송 메시지")
        }

        // when, then
        assertThatIllegalArgumentException().isThrownBy {
            ShippingMessage("")
        }
    }

    @Test
    fun equalsAndHashCode() {
        // given
        val shippingMessage1 = ShippingMessage("배송 메시지")
        val shippingMessage2 = ShippingMessage("배송 메시지")
        val shippingMessage3 = ShippingMessage("다른 배송 메시지")

        // when, then
        TestUtil.testEqualsAndHashCode(shippingMessage1, shippingMessage2, shippingMessage3)
    }
}
