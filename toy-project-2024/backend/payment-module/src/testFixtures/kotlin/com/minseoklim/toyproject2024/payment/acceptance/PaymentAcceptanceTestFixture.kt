package com.minseoklim.toyproject2024.payment.acceptance

import com.minseoklim.toyproject2024.test.util.RequestUtil
import com.minseoklim.toyproject2024.test.util.TestUtil.httpStatus
import io.restassured.response.ExtractableResponse
import io.restassured.response.Response
import org.assertj.core.api.Assertions.assertThat
import org.springframework.http.HttpStatus

object PaymentAcceptanceTestFixture {
    fun `카드 등록 요청`(accessToken: String, request: Map<String, Any?>): ExtractableResponse<Response> {
        return RequestUtil.post("/cards", accessToken, request)
    }

    fun `카드 등록됨`(response: ExtractableResponse<Response>) {
        assertThat(response.httpStatus()).isEqualTo(HttpStatus.CREATED)
    }

    fun `카드 목록 조회 요청`(accessToken: String): ExtractableResponse<Response> {
        return RequestUtil.get("/cards", accessToken)
    }

    fun `카드 목록 조회됨`(response: ExtractableResponse<Response>) {
        assertThat(response.httpStatus()).isEqualTo(HttpStatus.OK)
        assertThat(response.jsonPath().getList<Any>("content")).isNotEmpty
    }

    fun `카드 수정 요청`(accessToken: String, cardId: Int, request: Map<String, Any?>): ExtractableResponse<Response> {
        return RequestUtil.patch("/cards/$cardId", accessToken, request)
    }

    fun `카드 수정됨`(response: ExtractableResponse<Response>) {
        assertThat(response.httpStatus()).isEqualTo(HttpStatus.OK)
    }

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

    fun `카드 결제 취소 요청`(accessToken: String, paymentId: Int): ExtractableResponse<Response> {
        return RequestUtil.post("/cancel-card-payments/$paymentId", accessToken)
    }

    fun `카드 결제 취소됨`(response: ExtractableResponse<Response>) {
        assertThat(response.httpStatus()).isEqualTo(HttpStatus.OK)
    }

    fun `카드 삭제 요청`(accessToken: String, cardId: Int): ExtractableResponse<Response> {
        return RequestUtil.delete("/cards/$cardId", accessToken)
    }

    fun `카드 삭제됨`(response: ExtractableResponse<Response>) {
        assertThat(response.httpStatus()).isEqualTo(HttpStatus.NO_CONTENT)
    }
}
