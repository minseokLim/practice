package com.minseoklim.toyproject2024.order.domain.model

import com.minseoklim.toyproject2024.common.exception.NoPermissionException
import com.minseoklim.toyproject2024.common.util.TextEncryptUtil
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatNoException
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.security.crypto.encrypt.Encryptors
import org.springframework.test.util.ReflectionTestUtils

class OrderTest {
    @BeforeEach
    fun setUp() {
        TextEncryptUtil.init(Encryptors.noOpText())
    }

    @Test
    fun applyPaymentId() {
        // given
        val order = Order(
            orderName = "주문명",
            orderProducts = listOf(OrderProduct(1, 1)),
            shippingInfo = ShippingInfo(
                shippingMessage = "배송 메시지",
                address = Address(
                    value = "서울시 강남구 역삼동",
                    detail = "테헤란로 427",
                    zipCode = "06236"
                ),
                receiver = Receiver(
                    name = "홍길동",
                    phone = "010-1234-5678"
                )
            ),
            memberId = 1L
        )

        // when
        order.applyPaymentId(1)

        // then
        assertThat(order.paymentId).isEqualTo(1)
    }

    @Test
    fun changeToPreparing() {
        // given
        val order = Order(
            orderName = "주문명",
            orderProducts = listOf(OrderProduct(1, 1)),
            shippingInfo = ShippingInfo(
                shippingMessage = "배송 메시지",
                address = Address(
                    value = "서울시 강남구 역삼동",
                    detail = "테헤란로 427",
                    zipCode = "06236"
                ),
                receiver = Receiver(
                    name = "홍길동",
                    phone = "010-1234-5678"
                )
            ),
            memberId = 1L
        )

        // when
        order.changeToPreparing()

        // then
        assertThat(order.orderStatus).isEqualTo(OrderStatus.PREPARING)
    }

    @Test
    fun checkAuthority() {
        // given
        val order = Order(
            orderName = "주문명",
            orderProducts = listOf(OrderProduct(1, 1)),
            shippingInfo = ShippingInfo(
                shippingMessage = "배송 메시지",
                address = Address(
                    value = "서울시 강남구 역삼동",
                    detail = "테헤란로 427",
                    zipCode = "06236"
                ),
                receiver = Receiver(
                    name = "홍길동",
                    phone = "010-1234-5678"
                )
            ),
            memberId = 1L
        )

        // when, then
        assertThatNoException().isThrownBy {
            order.checkAuthority(1)
        }

        // when, then
        assertThatThrownBy {
            order.checkAuthority(2)
        }.isInstanceOf(NoPermissionException::class.java)
    }

    @Test
    fun cancel() {
        // given
        val order = Order(
            orderName = "주문명",
            orderProducts = listOf(OrderProduct(1, 1)),
            shippingInfo = ShippingInfo(
                shippingMessage = "배송 메시지",
                address = Address(
                    value = "서울시 강남구 역삼동",
                    detail = "테헤란로 427",
                    zipCode = "06236"
                ),
                receiver = Receiver(
                    name = "홍길동",
                    phone = "010-1234-5678"
                )
            ),
            memberId = 1L
        )

        // when
        order.cancel()

        // then
        assertThat(order.isCanceled).isTrue
    }

    @Test
    fun equalsAndHashCode() {
        // given
        val order1 = Order(
            orderName = "주문명",
            orderProducts = listOf(OrderProduct(1, 1)),
            shippingInfo = ShippingInfo(
                shippingMessage = "배송 메시지",
                address = Address(
                    value = "서울시 강남구 역삼동",
                    detail = "테헤란로 427",
                    zipCode = "06236"
                ),
                receiver = Receiver(
                    name = "홍길동",
                    phone = "010-1234-5678"
                )
            ),
            memberId = 1L
        )
        val order2 = Order(
            orderName = "주문명",
            orderProducts = listOf(OrderProduct(1, 1)),
            shippingInfo = ShippingInfo(
                shippingMessage = "배송 메시지",
                address = Address(
                    value = "서울시 강남구 역삼동",
                    detail = "테헤란로 427",
                    zipCode = "06236"
                ),
                receiver = Receiver(
                    name = "홍길동",
                    phone = "010-1234-5678"
                )
            ),
            memberId = 1L
        )
        val order3 = Order(
            orderName = "다른 주문명",
            orderProducts = listOf(OrderProduct(1, 1)),
            shippingInfo = ShippingInfo(
                shippingMessage = "배송 메시지",
                address = Address(
                    value = "서울시 강남구 역삼동",
                    detail = "테헤란로 427",
                    zipCode = "06236"
                ),
                receiver = Receiver(
                    name = "홍길동",
                    phone = "010-1234-5678"
                )
            ),
            memberId = 1L
        )
        ReflectionTestUtils.setField(order1, "id", 1L)
        ReflectionTestUtils.setField(order2, "id", 1L)

        // when, then
        assertThat(setOf(order1, order2, order3)).hasSize(2)
    }
}
