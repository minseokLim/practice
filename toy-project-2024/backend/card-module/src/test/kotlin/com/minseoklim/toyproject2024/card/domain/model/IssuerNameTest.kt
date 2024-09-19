package com.minseoklim.toyproject2024.card.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.assertj.core.api.Assertions.assertThatNoException
import org.junit.jupiter.api.Test

class IssuerNameTest {
    @Test
    fun constructor() {
        // when, then
        assertThatNoException().isThrownBy {
            IssuerName("삼성카드")
        }

        // when, then
        assertThatIllegalArgumentException().isThrownBy {
            IssuerName("")
        }
    }

    @Test
    fun equalsAndHashCode() {
        // given
        val issuerName1 = IssuerName("삼성카드")
        val issuerName2 = IssuerName("삼성카드")
        val issuerName3 = IssuerName("우리카드")

        // when, then
        assertThat(setOf(issuerName1, issuerName2, issuerName3)).hasSize(2)
    }
}
