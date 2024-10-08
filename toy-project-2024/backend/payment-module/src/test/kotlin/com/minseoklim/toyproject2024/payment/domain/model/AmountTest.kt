package com.minseoklim.toyproject2024.payment.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.assertj.core.api.Assertions.assertThatNoException
import org.junit.jupiter.api.Test

class AmountTest {
    @Test
    fun constructor() {
        // when, then
        assertThatNoException().isThrownBy {
            Amount(1000)
        }

        // when, then
        assertThatIllegalArgumentException().isThrownBy {
            Amount(0)
        }
    }

    @Test
    fun equalsAndHashCode() {
        // given
        val amount1 = Amount(1000)
        val amount2 = Amount(1000)
        val amount3 = Amount(2000)

        // when, then
        assertThat(setOf(amount1, amount2, amount3)).hasSize(2)
    }
}
