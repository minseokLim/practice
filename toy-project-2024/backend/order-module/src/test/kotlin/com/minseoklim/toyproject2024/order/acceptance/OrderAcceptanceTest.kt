package com.minseoklim.toyproject2024.order.acceptance

import com.minseoklim.toyproject2024.auth.acceptance.AuthAcceptanceTestFixture.`로그인 요청`
import com.minseoklim.toyproject2024.auth.acceptance.AuthAcceptanceTestUtil.extractAccessToken
import com.minseoklim.toyproject2024.card.acceptance.CardAcceptanceTestFixture.`카드 등록 요청`
import com.minseoklim.toyproject2024.member.acceptance.MemberAcceptanceTestFixture.`회원 가입 요청`
import com.minseoklim.toyproject2024.order.acceptance.OrderAcceptanceTestFixture.`인증 결제 주문 생성 요청`
import com.minseoklim.toyproject2024.order.acceptance.OrderAcceptanceTestFixture.`인증 결제 주문 생성됨`
import com.minseoklim.toyproject2024.order.acceptance.OrderAcceptanceTestFixture.`인증 결제 주문 실패 처리 요청`
import com.minseoklim.toyproject2024.order.acceptance.OrderAcceptanceTestFixture.`인증 결제 주문 실패 처리됨`
import com.minseoklim.toyproject2024.order.acceptance.OrderAcceptanceTestFixture.`인증 결제 주문 완료 처리 요청`
import com.minseoklim.toyproject2024.order.acceptance.OrderAcceptanceTestFixture.`인증 결제 주문 완료 처리됨`
import com.minseoklim.toyproject2024.order.acceptance.OrderAcceptanceTestFixture.`주문 목록 조회 요청`
import com.minseoklim.toyproject2024.order.acceptance.OrderAcceptanceTestFixture.`주문 목록 조회됨`
import com.minseoklim.toyproject2024.order.acceptance.OrderAcceptanceTestFixture.`주문 조회 요청`
import com.minseoklim.toyproject2024.order.acceptance.OrderAcceptanceTestFixture.`주문 조회됨`
import com.minseoklim.toyproject2024.order.acceptance.OrderAcceptanceTestFixture.`주문 취소 요청`
import com.minseoklim.toyproject2024.order.acceptance.OrderAcceptanceTestFixture.`주문 취소됨`
import com.minseoklim.toyproject2024.order.acceptance.OrderAcceptanceTestFixture.`카드 주문 요청`
import com.minseoklim.toyproject2024.order.acceptance.OrderAcceptanceTestFixture.`카드 주문됨`
import com.minseoklim.toyproject2024.product.acceptance.ProductAcceptanceTestFixture.`상품 등록 요청`
import com.minseoklim.toyproject2024.test.AcceptanceTest
import com.minseoklim.toyproject2024.test.util.TestUtil.extractId
import com.minseoklim.toyproject2024.test.util.TestUtil.extractIds
import org.junit.jupiter.api.Test

class OrderAcceptanceTest : AcceptanceTest() {

    @Test
    fun `주문 서비스`() {
        // given
        val memberJoinRequest = mapOf(
            "loginId" to MEMBER_ID,
            "password" to PASSWORD,
            "name" to "testName",
            "email" to "test@test.com"
        )
        `회원 가입 요청`(memberJoinRequest)

        // given
        val loginRequest = mapOf(
            "loginId" to MEMBER_ID,
            "password" to PASSWORD
        )
        val loginResponse = `로그인 요청`(loginRequest)
        val accessToken = loginResponse.extractAccessToken()

        // given
        val cardRegisterRequest = mapOf(
            "cardNumber" to "1234-5678-1234-5678",
            "cardExpiry" to "2025-12",
            "birth" to "990101",
            "pwd2digit" to "12",
            "issuerName" to "삼성카드"
        )
        val cardRegisterResponse = `카드 등록 요청`(accessToken, cardRegisterRequest)
        val cardId = cardRegisterResponse.extractId()

        // given
        val productRegisterRequest = mapOf(
            "name" to "테스트 상품",
            "price" to 1000,
            "stockQuantity" to 100
        )
        val productRegisterResponse = `상품 등록 요청`(accessToken, productRegisterRequest)
        val productId = productRegisterResponse.extractId()

        // given
        val cardOrderRequest = mapOf(
            "orderProducts" to listOf(
                mapOf(
                    "productId" to productId,
                    "quantity" to 2
                )
            ),
            "shippingInfo" to mapOf(
                "shippingMessage" to "조심히 잘 가져다 주세요.",
                "address" to mapOf(
                    "value" to "서울시 강남구 역삼동",
                    "detail" to "테헤란로 427",
                    "zipCode" to "06236"
                ),
                "receiver" to mapOf(
                    "name" to "홍길동",
                    "phone" to "010-1234-5678"
                )
            ),
            "cardId" to cardId
        )

        // when
        val cardOrderResponse = `카드 주문 요청`(accessToken, cardOrderRequest)

        // then
        `카드 주문됨`(cardOrderResponse)

        // when
        val orderListResponse = `주문 목록 조회 요청`(accessToken)

        // then
        `주문 목록 조회됨`(orderListResponse)

        // given
        val orderId = orderListResponse.extractIds()[0]

        // when
        val orderResponse = `주문 조회 요청`(accessToken, orderId)

        // then
        `주문 조회됨`(orderResponse)

        // when
        val orderCancelResponse = `주문 취소 요청`(accessToken, orderId)

        // then
        `주문 취소됨`(orderCancelResponse)

        // given
        val checkOutVerifiedOrderRequest = mapOf(
            "orderProducts" to listOf(
                mapOf(
                    "productId" to productId,
                    "quantity" to 2
                )
            ),
            "shippingInfo" to mapOf(
                "shippingMessage" to "조심히 잘 가져다 주세요.",
                "address" to mapOf(
                    "value" to "서울시 강남구 역삼동",
                    "detail" to "테헤란로 427",
                    "zipCode" to "06236"
                ),
                "receiver" to mapOf(
                    "name" to "홍길동",
                    "phone" to "010-1234-5678"
                )
            )
        )

        // when
        val checkOutVerifiedOrderResponse = `인증 결제 주문 생성 요청`(accessToken, checkOutVerifiedOrderRequest)

        // then
        `인증 결제 주문 생성됨`(checkOutVerifiedOrderResponse)

        // given
        val verifiedOrderId = checkOutVerifiedOrderResponse.extractId()
        val failVerifiedOrderRequest = mapOf(
            "orderId" to verifiedOrderId
        )

        // when
        val failVerifiedOrderResponse = `인증 결제 주문 실패 처리 요청`(accessToken, failVerifiedOrderRequest)

        // then
        `인증 결제 주문 실패 처리됨`(failVerifiedOrderResponse)

        // given
        val completeVerifiedOrderRequest = mapOf(
            "orderId" to verifiedOrderId
        )

        // when
        val completeVerifiedOrderResponse = `인증 결제 주문 완료 처리 요청`(accessToken, completeVerifiedOrderRequest)

        // then
        `인증 결제 주문 완료 처리됨`(completeVerifiedOrderResponse)

        // when
        val verifiedOrderCancelResponse = `주문 취소 요청`(accessToken, verifiedOrderId)

        // then
        `주문 취소됨`(verifiedOrderCancelResponse)
    }

    companion object {
        private const val MEMBER_ID = "test1234"
        private const val PASSWORD = "password"
    }
}
