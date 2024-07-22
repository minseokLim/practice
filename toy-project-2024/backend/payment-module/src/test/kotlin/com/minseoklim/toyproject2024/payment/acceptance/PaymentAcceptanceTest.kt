package com.minseoklim.toyproject2024.payment.acceptance

import com.minseoklim.toyproject2024.auth.acceptance.AuthAcceptanceTestFixture.`로그인 요청`
import com.minseoklim.toyproject2024.auth.acceptance.AuthAcceptanceTestUtil.extractAccessToken
import com.minseoklim.toyproject2024.member.acceptance.MemberAcceptanceTestFixture.`회원 가입 요청`
import com.minseoklim.toyproject2024.payment.acceptance.PaymentAcceptanceTestFixture.`결제 정보 목록 조회 요청`
import com.minseoklim.toyproject2024.payment.acceptance.PaymentAcceptanceTestFixture.`결제 정보 목록 조회됨`
import com.minseoklim.toyproject2024.payment.acceptance.PaymentAcceptanceTestFixture.`카드 결제 요청`
import com.minseoklim.toyproject2024.payment.acceptance.PaymentAcceptanceTestFixture.`카드 결제 취소 요청`
import com.minseoklim.toyproject2024.payment.acceptance.PaymentAcceptanceTestFixture.`카드 결제 취소됨`
import com.minseoklim.toyproject2024.payment.acceptance.PaymentAcceptanceTestFixture.`카드 결제됨`
import com.minseoklim.toyproject2024.payment.acceptance.PaymentAcceptanceTestFixture.`카드 등록 요청`
import com.minseoklim.toyproject2024.payment.acceptance.PaymentAcceptanceTestFixture.`카드 등록됨`
import com.minseoklim.toyproject2024.payment.acceptance.PaymentAcceptanceTestFixture.`카드 목록 조회 요청`
import com.minseoklim.toyproject2024.payment.acceptance.PaymentAcceptanceTestFixture.`카드 목록 조회됨`
import com.minseoklim.toyproject2024.payment.acceptance.PaymentAcceptanceTestFixture.`카드 삭제 요청`
import com.minseoklim.toyproject2024.payment.acceptance.PaymentAcceptanceTestFixture.`카드 삭제됨`
import com.minseoklim.toyproject2024.payment.acceptance.PaymentAcceptanceTestFixture.`카드 수정 요청`
import com.minseoklim.toyproject2024.payment.acceptance.PaymentAcceptanceTestFixture.`카드 수정됨`
import com.minseoklim.toyproject2024.test.AcceptanceTest
import com.minseoklim.toyproject2024.test.util.TestUtil.extractIds
import org.junit.jupiter.api.Test
import java.util.UUID

class PaymentAcceptanceTest : AcceptanceTest() {

    @Test
    fun `카드 결제 서비스`() {
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
            "pwd2digit" to "12"
        )

        // when
        val cardRegisterResponse = `카드 등록 요청`(accessToken, cardRegisterRequest)

        // then
        `카드 등록됨`(cardRegisterResponse)

        // when
        val cardListResponse = `카드 목록 조회 요청`(accessToken)

        // then
        `카드 목록 조회됨`(cardListResponse)

        // given
        val cardId = cardListResponse.extractIds()[0]
        val cardUpdateRequest = mapOf(
            "pwd2digit" to "34"
        )

        // when
        val cardUpdateResponse = `카드 수정 요청`(accessToken, cardId, cardUpdateRequest)

        // then
        `카드 수정됨`(cardUpdateResponse)

        // given
        val cardPaymentRequest = mapOf(
            "cardId" to cardId,
            "amount" to 100,
            "productName" to "테스트 상품"
        )

        // when
        val cardPaymentResponse = `카드 결제 요청`(accessToken, cardPaymentRequest)

        // then
        `카드 결제됨`(cardPaymentResponse)

        // when
        val cardPaymentListResponse = `결제 정보 목록 조회 요청`(accessToken)

        // then
        `결제 정보 목록 조회됨`(cardPaymentListResponse)

        // given
        val paymentId = cardPaymentListResponse.extractIds()[0]

        // when
        val cardPaymentCancelResponse = `카드 결제 취소 요청`(accessToken, paymentId)

        // then
        `카드 결제 취소됨`(cardPaymentCancelResponse)

        // when
        val cardDeleteResponse = `카드 삭제 요청`(accessToken, cardId)

        // then
        `카드 삭제됨`(cardDeleteResponse)
    }

    companion object {
        private const val MEMBER_ID = "test1234"
        private const val PASSWORD = "password"
    }
}
