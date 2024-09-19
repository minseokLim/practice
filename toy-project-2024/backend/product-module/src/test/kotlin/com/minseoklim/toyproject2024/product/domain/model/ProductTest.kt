package com.minseoklim.toyproject2024.product.domain.model

import com.minseoklim.toyproject2024.common.exception.NoPermissionException
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatNoException
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.springframework.test.util.ReflectionTestUtils

class ProductTest {
    @Test
    fun update() {
        // given
        val product = Product(
            name = "test",
            price = 1000,
            stockQuantity = 100,
            memberId = 1
        )
        val other = Product(
            name = "other",
            price = 2000,
            stockQuantity = 200,
            memberId = 1
        )

        // when
        product.update(other)

        // then
        assertThat(product.name).isEqualTo(other.name)
        assertThat(product.price).isEqualTo(other.price)
    }

    @Test
    fun addStockQuantity() {
        // given
        val product = Product(
            name = "test",
            price = 1000,
            stockQuantity = 100,
            memberId = 1
        )

        // when
        product.addStockQuantity(100)

        // then
        assertThat(product.stockQuantity).isEqualTo(StockQuantity(200))
    }

    @Test
    fun removeStockQuantity() {
        // given
        val product = Product(
            name = "test",
            price = 1000,
            stockQuantity = 100,
            memberId = 1
        )

        // when
        product.removeStockQuantity(50)

        // then
        assertThat(product.stockQuantity).isEqualTo(StockQuantity(50))
    }

    @Test
    fun isSoldOut() {
        // given
        val product = Product(
            name = "test",
            price = 1000,
            stockQuantity = 0,
            memberId = 1
        )

        // when
        val result = product.isSoldOut()

        // then
        assertThat(result).isTrue
    }

    @Test
    fun checkAuthority() {
        // given
        val product = Product(
            name = "test",
            price = 1000,
            stockQuantity = 100,
            memberId = 1
        )

        // when, then
        assertThatNoException().isThrownBy { product.checkAuthority(1) }

        // when, then
        assertThatThrownBy {
            product.checkAuthority(2)
        }.isInstanceOf(NoPermissionException::class.java)
    }

    @Test
    fun delete() {
        // given
        val product = Product(
            name = "test",
            price = 1000,
            stockQuantity = 100,
            memberId = 1
        )

        // when
        product.delete()

        // then
        assertThat(product.isDeleted).isTrue
    }

    @Test
    fun equalsAndHashCode() {
        // given
        val product1 = Product(
            name = "test",
            price = 1000,
            stockQuantity = 100,
            memberId = 1
        )
        val product2 = Product(
            name = "test",
            price = 1000,
            stockQuantity = 100,
            memberId = 1
        )
        val product3 = Product(
            name = "other",
            price = 2000,
            stockQuantity = 200,
            memberId = 1
        )
        ReflectionTestUtils.setField(product1, "id", 1)
        ReflectionTestUtils.setField(product2, "id", 1)

        // when, then
        assertThat(setOf(product1, product2, product3)).hasSize(2)
    }
}
