package com.minseoklim.toyproject2024.payment.acceptance

import com.minseoklim.toyproject2024.auth.acceptance.AuthAcceptanceTestFixture.`로그인 요청`
import com.minseoklim.toyproject2024.auth.acceptance.AuthAcceptanceTestUtil.extractAccessToken
import com.minseoklim.toyproject2024.card.acceptance.CardAcceptanceTestFixture.`카드 등록 요청`
import com.minseoklim.toyproject2024.card.acceptance.CardAcceptanceTestFixture.`카드 목록 조회 요청`
import com.minseoklim.toyproject2024.member.acceptance.MemberAcceptanceTestFixture.`회원 가입 요청`
import com.minseoklim.toyproject2024.payment.acceptance.PaymentAcceptanceTestFixture.`결제 정보 목록 조회 요청`
import com.minseoklim.toyproject2024.payment.acceptance.PaymentAcceptanceTestFixture.`결제 정보 목록 조회됨`
import com.minseoklim.toyproject2024.payment.acceptance.PaymentAcceptanceTestFixture.`결제 취소 요청`
import com.minseoklim.toyproject2024.payment.acceptance.PaymentAcceptanceTestFixture.`결제 취소됨`
import com.minseoklim.toyproject2024.payment.acceptance.PaymentAcceptanceTestFixture.`인증 결제 실패 처리 요청`
import com.minseoklim.toyproject2024.payment.acceptance.PaymentAcceptanceTestFixture.`인증 결제 실패 처리됨`
import com.minseoklim.toyproject2024.payment.acceptance.PaymentAcceptanceTestFixture.`인증 결제 완료 처리 요청`
import com.minseoklim.toyproject2024.payment.acceptance.PaymentAcceptanceTestFixture.`인증 결제 완료 처리됨`
import com.minseoklim.toyproject2024.payment.acceptance.PaymentAcceptanceTestFixture.`인증 결제 정보 생성 요청`
import com.minseoklim.toyproject2024.payment.acceptance.PaymentAcceptanceTestFixture.`인증 결제 정보 생성됨`
import com.minseoklim.toyproject2024.payment.acceptance.PaymentAcceptanceTestFixture.`카드 결제 요청`
import com.minseoklim.toyproject2024.payment.acceptance.PaymentAcceptanceTestFixture.`카드 결제됨`
import com.minseoklim.toyproject2024.test.AcceptanceTest
import com.minseoklim.toyproject2024.test.util.TestUtil.extractId
import com.minseoklim.toyproject2024.test.util.TestUtil.extractIds
import org.junit.jupiter.api.Test

class PaymentAcceptanceTest : AcceptanceTest() {

    @Test
    fun `결제 서비스`() {
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
        `카드 등록 요청`(accessToken, cardRegisterRequest)

        // given
        val cardListResponse = `카드 목록 조회 요청`(accessToken)
        val cardId = cardListResponse.extractIds()[0]

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
        val paymentListResponse = `결제 정보 목록 조회 요청`(accessToken)

        // then
        `결제 정보 목록 조회됨`(paymentListResponse)

        // given
        val cardPaymentId = paymentListResponse.extractIds()[0]

        // when
        val cardPaymentCancelResponse = `결제 취소 요청`(accessToken, cardPaymentId)

        // then
        `결제 취소됨`(cardPaymentCancelResponse)

        // given
        val checkOutVerifiedPaymentRequest = mapOf(
            "amount" to 100,
            "productName" to "테스트 상품"
        )

        // when
        val checkOutVerifiedPaymentResponse = `인증 결제 정보 생성 요청`(accessToken, checkOutVerifiedPaymentRequest)

        // then
        `인증 결제 정보 생성됨`(checkOutVerifiedPaymentResponse)

        // given
        val paymentUid = checkOutVerifiedPaymentResponse.jsonPath().get<String>("paymentUid")
        val failVerifiedPaymentRequest = mapOf(
            "paymentUid" to paymentUid
        )

        // when
        val failVerifiedPaymentResponse = `인증 결제 실패 처리 요청`(accessToken, failVerifiedPaymentRequest)

        // then
        `인증 결제 실패 처리됨`(failVerifiedPaymentResponse)

        // given
        val succeedVerifiedPaymentRequest = mapOf(
            "paymentUid" to paymentUid
        )

        // when
        val succeedVerifiedPaymentResponse = `인증 결제 완료 처리 요청`(accessToken, succeedVerifiedPaymentRequest)

        // then
        `인증 결제 완료 처리됨`(succeedVerifiedPaymentResponse)

        // given
        val verifiedPaymentId = checkOutVerifiedPaymentResponse.extractId()

        // when
        val verifiedPaymentCancelResponse = `결제 취소 요청`(accessToken, verifiedPaymentId)

        // then
        `결제 취소됨`(verifiedPaymentCancelResponse)
    }

    companion object {
        private const val MEMBER_ID = "test1234"
        private const val PASSWORD = "password"
    }
}
