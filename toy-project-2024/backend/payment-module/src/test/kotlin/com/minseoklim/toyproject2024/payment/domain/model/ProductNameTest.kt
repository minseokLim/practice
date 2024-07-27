package com.minseoklim.toyproject2024.payment.domain.model

import com.minseoklim.toyproject2024.test.util.TestUtil
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.assertj.core.api.Assertions.assertThatNoException
import org.junit.jupiter.api.Test

class ProductNameTest {
    @Test
    fun constructor() {
        // when, then
        assertThatNoException().isThrownBy {
            ProductName("테스트 상품")
        }

        // when, then
        assertThatIllegalArgumentException().isThrownBy {
            ProductName("")
        }
    }

    @Test
    fun equalsAndHashCode() {
        // given
        val productName1 = ProductName("테스트 상품")
        val productName2 = ProductName("테스트 상품")
        val productName3 = ProductName("테스트 상품2")

        // when, then
        TestUtil.testEqualsAndHashCode(productName1, productName2, productName3)
    }
}
