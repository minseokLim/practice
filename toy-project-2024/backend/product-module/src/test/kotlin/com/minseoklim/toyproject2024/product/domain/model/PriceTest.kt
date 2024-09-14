package com.minseoklim.toyproject2024.product.domain.model

import com.minseoklim.toyproject2024.test.util.TestUtil
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.assertj.core.api.Assertions.assertThatNoException
import org.junit.jupiter.api.Test

class PriceTest {
    @Test
    fun constructor() {
        // when, then
        assertThatNoException().isThrownBy {
            Price(1000)
        }

        // when, then
        assertThatIllegalArgumentException().isThrownBy {
            Price(-1)
        }
    }

    @Test
    fun equalsAndHashCode() {
        // given
        val price1 = Price(1000)
        val price2 = Price(1000)
        val price3 = Price(2000)

        // when, then
        TestUtil.testEqualsAndHashCode(price1, price2, price3)
    }
}
