package com.minseoklim.toyproject2024.payment.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class VerifiedPaymentTest {
    @Test
    fun fail() {
        // given
        val verifiedPayment = VerifiedPayment(1000, "test", 1)

        // when
        verifiedPayment.fail()

        // then
        assertThat(verifiedPayment.status).isEqualTo(VerifiedPaymentStatus.FAILED)
    }

    @Test
    fun complete() {
        // given
        val verifiedPayment = VerifiedPayment(1000, "test", 1)

        // when
        verifiedPayment.complete()

        // then
        assertThat(verifiedPayment.status).isEqualTo(VerifiedPaymentStatus.COMPLETED)
    }

    @Test
    fun tamper() {
        // given
        val verifiedPayment = VerifiedPayment(1000, "test", 1)

        // when
        verifiedPayment.tamper()

        // then
        assertThat(verifiedPayment.status).isEqualTo(VerifiedPaymentStatus.TAMPERED)
    }
}
