package com.minseoklim.toyproject2024.card.acceptance

import com.minseoklim.toyproject2024.test.util.RequestUtil
import com.minseoklim.toyproject2024.test.util.TestUtil.httpStatus
import io.restassured.response.ExtractableResponse
import io.restassured.response.Response
import org.assertj.core.api.Assertions.assertThat
import org.springframework.http.HttpStatus

@Suppress("ktlint:standard:function-naming")
object CardAcceptanceTestFixture {
    fun `카드 등록 요청`(
        accessToken: String,
        request: Map<String, Any?>
    ): ExtractableResponse<Response> {
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

    fun `카드 삭제 요청`(
        accessToken: String,
        cardId: Long
    ): ExtractableResponse<Response> {
        return RequestUtil.delete("/cards/$cardId", accessToken)
    }

    fun `카드 삭제됨`(response: ExtractableResponse<Response>) {
        assertThat(response.httpStatus()).isEqualTo(HttpStatus.NO_CONTENT)
    }
}
