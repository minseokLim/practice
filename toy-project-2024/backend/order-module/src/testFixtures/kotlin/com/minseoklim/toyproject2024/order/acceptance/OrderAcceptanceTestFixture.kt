package com.minseoklim.toyproject2024.order.acceptance

import com.minseoklim.toyproject2024.test.util.RequestUtil
import com.minseoklim.toyproject2024.test.util.TestUtil.httpStatus
import io.restassured.response.ExtractableResponse
import io.restassured.response.Response
import org.assertj.core.api.Assertions.assertThat
import org.springframework.http.HttpStatus

@Suppress("ktlint:standard:function-naming")
object OrderAcceptanceTestFixture {
    fun `카드 주문 요청`(
        accessToken: String,
        request: Map<String, Any?>
    ): ExtractableResponse<Response> {
        return RequestUtil.post("/card-orders", accessToken, request)
    }

    fun `카드 주문됨`(response: ExtractableResponse<Response>) {
        assertThat(response.httpStatus()).isEqualTo(HttpStatus.OK)
    }

    fun `주문 목록 조회 요청`(accessToken: String): ExtractableResponse<Response> {
        return RequestUtil.get("/orders", accessToken)
    }

    fun `주문 목록 조회됨`(response: ExtractableResponse<Response>) {
        assertThat(response.httpStatus()).isEqualTo(HttpStatus.OK)
        assertThat(response.jsonPath().getList<Any>("content")).isNotEmpty
    }

    fun `주문 조회 요청`(
        accessToken: String,
        orderId: Int
    ): ExtractableResponse<Response> {
        return RequestUtil.get("/orders/$orderId", accessToken)
    }

    fun `주문 조회됨`(response: ExtractableResponse<Response>) {
        assertThat(response.httpStatus()).isEqualTo(HttpStatus.OK)
    }

    fun `주문 취소 요청`(
        accessToken: String,
        orderId: Int
    ): ExtractableResponse<Response> {
        return RequestUtil.post("/cancel-orders/$orderId", accessToken)
    }

    fun `주문 취소됨`(response: ExtractableResponse<Response>) {
        assertThat(response.httpStatus()).isEqualTo(HttpStatus.OK)
    }

    fun `인증 결제 주문 생성 요청`(
        accessToken: String,
        request: Map<String, Any?>
    ): ExtractableResponse<Response> {
        return RequestUtil.post("/verified-orders", accessToken, request)
    }

    fun `인증 결제 주문 생성됨`(response: ExtractableResponse<Response>) {
        assertThat(response.httpStatus()).isEqualTo(HttpStatus.OK)
    }

    fun `인증 결제 주문 실패 처리 요청`(
        accessToken: String,
        request: Map<String, Any?>
    ): ExtractableResponse<Response> {
        return RequestUtil.post("/fail-verified-order", accessToken, request)
    }

    fun `인증 결제 주문 실패 처리됨`(response: ExtractableResponse<Response>) {
        assertThat(response.httpStatus()).isEqualTo(HttpStatus.OK)
    }

    fun `인증 결제 주문 완료 처리 요청`(
        accessToken: String,
        request: Map<String, Any?>
    ): ExtractableResponse<Response> {
        return RequestUtil.post("/complete-verified-order", accessToken, request)
    }

    fun `인증 결제 주문 완료 처리됨`(response: ExtractableResponse<Response>) {
        assertThat(response.httpStatus()).isEqualTo(HttpStatus.OK)
    }
}
