package com.minseoklim.toyproject2024.product.acceptance

import com.minseoklim.toyproject2024.test.util.RequestUtil
import com.minseoklim.toyproject2024.test.util.TestUtil.extractId
import com.minseoklim.toyproject2024.test.util.TestUtil.httpStatus
import io.restassured.response.ExtractableResponse
import io.restassured.response.Response
import org.assertj.core.api.Assertions.assertThat
import org.springframework.http.HttpStatus

@Suppress("ktlint:standard:function-naming")
object ProductAcceptanceTestFixture {
    fun `상품 등록 요청`(
        accessToken: String,
        request: Map<String, Any?>
    ): ExtractableResponse<Response> {
        return RequestUtil.post("/products", accessToken, request)
    }

    fun `상품 등록됨`(response: ExtractableResponse<Response>) {
        assertThat(response.httpStatus()).isEqualTo(HttpStatus.CREATED)
        assertThat(response.extractId()).isNotNull
    }

    fun `상품 목록 조회 요청`(accessToken: String): ExtractableResponse<Response> {
        return RequestUtil.get("/products", accessToken)
    }

    fun `상품 목록 조회됨`(response: ExtractableResponse<Response>) {
        assertThat(response.httpStatus()).isEqualTo(HttpStatus.OK)
        assertThat(response.jsonPath().getList<Any>("content")).isNotEmpty
    }

    fun `상품 조회 요청`(
        accessToken: String,
        productId: Long
    ): ExtractableResponse<Response> {
        return RequestUtil.get("/products/$productId", accessToken)
    }

    fun `상품 조회됨`(response: ExtractableResponse<Response>) {
        assertThat(response.httpStatus()).isEqualTo(HttpStatus.OK)
        assertThat(response.extractId()).isNotNull
    }

    fun `상품 수정 요청`(
        accessToken: String,
        productId: Long,
        request: Map<String, Any?>
    ): ExtractableResponse<Response> {
        return RequestUtil.put("/products/$productId", accessToken, request)
    }

    fun `상품 수정됨`(response: ExtractableResponse<Response>) {
        assertThat(response.httpStatus()).isEqualTo(HttpStatus.OK)
        assertThat(response.extractId()).isNotNull
    }

    fun `상품 재고 추가 요청`(
        accessToken: String,
        productId: Long,
        request: Map<String, Any?>
    ): ExtractableResponse<Response> {
        return RequestUtil.post("/products/$productId/add-stock-quantity", accessToken, request)
    }

    fun `상품 재고 추가됨`(response: ExtractableResponse<Response>) {
        assertThat(response.httpStatus()).isEqualTo(HttpStatus.OK)
    }

    fun `상품 재고 감소 요청`(
        accessToken: String,
        productId: Long,
        request: Map<String, Any?>
    ): ExtractableResponse<Response> {
        return RequestUtil.post("/products/$productId/remove-stock-quantity", accessToken, request)
    }

    fun `상품 재고 감소됨`(response: ExtractableResponse<Response>) {
        assertThat(response.httpStatus()).isEqualTo(HttpStatus.OK)
    }

    fun `상품 삭제 요청`(
        accessToken: String,
        productId: Long
    ): ExtractableResponse<Response> {
        return RequestUtil.delete("/products/$productId", accessToken)
    }

    fun `상품 삭제됨`(response: ExtractableResponse<Response>) {
        assertThat(response.httpStatus()).isEqualTo(HttpStatus.NO_CONTENT)
    }
}
