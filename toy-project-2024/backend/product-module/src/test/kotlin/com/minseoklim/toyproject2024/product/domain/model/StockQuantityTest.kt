package com.minseoklim.toyproject2024.product.domain.model

import com.minseoklim.toyproject2024.test.util.TestUtil
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.assertj.core.api.Assertions.assertThatNoException
import org.junit.jupiter.api.Test

class StockQuantityTest {
    @Test
    fun constructor() {
        // when, then
        assertThatNoException().isThrownBy {
            StockQuantity(100)
        }

        // when, then
        assertThatIllegalArgumentException().isThrownBy {
            StockQuantity(-1)
        }
    }

    @Test
    fun equalsAndHashCode() {
        // given
        val stockQuantity1 = StockQuantity(100)
        val stockQuantity2 = StockQuantity(100)
        val stockQuantity3 = StockQuantity(200)

        // when, then
        TestUtil.testEqualsAndHashCode(stockQuantity1, stockQuantity2, stockQuantity3)
    }

    @Test
    fun isSoldOut() {
        // given
        val stockQuantity = StockQuantity(0)

        // when
        val result = stockQuantity.isSoldOut()

        // then
        assertThat(result).isTrue
    }
}
