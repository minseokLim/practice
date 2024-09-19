package com.minseoklim.toyproject2024.payment.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.test.util.ReflectionTestUtils

class PaymentUidTest {
    @Test
    fun equalsAndHashCode() {
        // given
        val paymentUid1 = PaymentUid()
        val paymentUid2 = PaymentUid()
        val paymentUid3 = PaymentUid()
        ReflectionTestUtils.setField(paymentUid2, "value", paymentUid1.value)

        // when, then
        assertThat(setOf(paymentUid1, paymentUid2, paymentUid3)).hasSize(2)
    }
}
