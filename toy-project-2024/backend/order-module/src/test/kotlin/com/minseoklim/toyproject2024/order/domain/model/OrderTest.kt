package com.minseoklim.toyproject2024.order.domain.model

import com.minseoklim.toyproject2024.common.util.TextEncryptUtil
import com.minseoklim.toyproject2024.test.util.TestUtil
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
    fun equalsAndHashCode() {
        // given
        val order1 = Order(
            orderName = "주문명",
            orderProducts = listOf(OrderProduct(1, 1)),
            shippingMessage = "배송 메시지",
            address = "서울시 강남구 역삼동",
            addressDetail = "테헤란로 427",
            zipCode = "06236",
            receiverName = "홍길동",
            receiverPhone = "010-1234-5678",
            memberId = 1
        )
        val order2 = Order(
            orderName = "주문명",
            orderProducts = listOf(OrderProduct(1, 1)),
            shippingMessage = "배송 메시지",
            address = "서울시 강남구 역삼동",
            addressDetail = "테헤란로 427",
            zipCode = "06236",
            receiverName = "홍길동",
            receiverPhone = "010-1234-5678",
            memberId = 1
        )
        val order3 = Order(
            orderName = "다른 주문명",
            orderProducts = listOf(OrderProduct(1, 1)),
            shippingMessage = "배송 메시지",
            address = "서울시 강남구 역삼동",
            addressDetail = "테헤란로 427",
            zipCode = "06236",
            receiverName = "홍길동",
            receiverPhone = "010-1234-5678",
            memberId = 1
        )
        ReflectionTestUtils.setField(order1, "id", 1)
        ReflectionTestUtils.setField(order2, "id", 1)

        // when, then
        TestUtil.testEqualsAndHashCode(order1, order2, order3)
    }
}
