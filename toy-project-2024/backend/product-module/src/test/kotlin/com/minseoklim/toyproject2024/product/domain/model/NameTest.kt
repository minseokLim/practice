package com.minseoklim.toyproject2024.product.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.assertj.core.api.Assertions.assertThatNoException
import org.junit.jupiter.api.Test

class NameTest {
    @Test
    fun constructor() {
        // when, then
        assertThatNoException().isThrownBy {
            Name("테스트 상품")
        }

        // when, then
        assertThatIllegalArgumentException().isThrownBy {
            Name("")
        }
    }

    @Test
    fun equalsAndHashCode() {
        // given
        val name1 = Name("테스트 상품")
        val name2 = Name("테스트 상품")
        val name3 = Name("other")

        // when, then
        assertThat(setOf(name1, name2, name3)).hasSize(2)
    }
}
