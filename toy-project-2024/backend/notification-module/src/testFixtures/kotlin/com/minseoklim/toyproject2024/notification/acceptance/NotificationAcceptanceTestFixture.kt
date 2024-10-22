package com.minseoklim.toyproject2024.notification.acceptance

import com.minseoklim.toyproject2024.test.util.RequestUtil
import com.minseoklim.toyproject2024.test.util.TestUtil.httpStatus
import io.restassured.response.ExtractableResponse
import io.restassured.response.Response
import org.assertj.core.api.Assertions.assertThat
import org.springframework.http.HttpStatus

@Suppress("ktlint:standard:function-naming")
object NotificationAcceptanceTestFixture {
    fun `푸시 토큰 등록 요청`(
        accessToken: String,
        request: Map<String, Any?>
    ): ExtractableResponse<Response> {
        return RequestUtil.post("/push-tokens", accessToken, request)
    }

    fun `푸시 토큰 등록됨`(response: ExtractableResponse<Response>) {
        assertThat(response.httpStatus()).isEqualTo(HttpStatus.OK)
    }

    fun `푸시 전송 요청`(
        accessToken: String,
        request: Map<String, Any?>
    ): ExtractableResponse<Response> {
        return RequestUtil.post("/notifications", accessToken, request)
    }

    fun `푸시 전송됨`(response: ExtractableResponse<Response>) {
        assertThat(response.httpStatus()).isEqualTo(HttpStatus.OK)
    }
}
