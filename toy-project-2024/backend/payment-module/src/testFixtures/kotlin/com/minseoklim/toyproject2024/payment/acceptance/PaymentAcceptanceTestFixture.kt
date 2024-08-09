package com.minseoklim.toyproject2024.payment.acceptance

import com.minseoklim.toyproject2024.test.util.RequestUtil
import com.minseoklim.toyproject2024.test.util.TestUtil.httpStatus
import io.restassured.response.ExtractableResponse
import io.restassured.response.Response
import org.assertj.core.api.Assertions.assertThat
import org.springframework.http.HttpStatus

object PaymentAcceptanceTestFixture {
    fun `카드 결제 요청`(accessToken: String, request: Map<String, Any?>): ExtractableResponse<Response> {
        return RequestUtil.post("/card-payments", accessToken, request)
    }

    fun `카드 결제됨`(response: ExtractableResponse<Response>) {
        assertThat(response.httpStatus()).isEqualTo(HttpStatus.OK)
    }

    fun `결제 정보 목록 조회 요청`(accessToken: String): ExtractableResponse<Response> {
        return RequestUtil.get("/payments", accessToken)
    }

    fun `결제 정보 목록 조회됨`(response: ExtractableResponse<Response>) {
        assertThat(response.httpStatus()).isEqualTo(HttpStatus.OK)
        assertThat(response.jsonPath().getList<Any>("content")).isNotEmpty
    }

    fun `결제 취소 요청`(accessToken: String, paymentId: Int): ExtractableResponse<Response> {
        return RequestUtil.post("/cancel-payments/$paymentId", accessToken)
    }

    fun `결제 취소됨`(response: ExtractableResponse<Response>) {
        assertThat(response.httpStatus()).isEqualTo(HttpStatus.OK)
    }

    fun `인증 결제 정보 생성 요청`(accessToken: String, request: Map<String, Any?>): ExtractableResponse<Response> {
        return RequestUtil.post("/verified-payments", accessToken, request)
    }

    fun `인증 결제 정보 생성됨`(response: ExtractableResponse<Response>) {
        assertThat(response.httpStatus()).isEqualTo(HttpStatus.OK)
    }
}
