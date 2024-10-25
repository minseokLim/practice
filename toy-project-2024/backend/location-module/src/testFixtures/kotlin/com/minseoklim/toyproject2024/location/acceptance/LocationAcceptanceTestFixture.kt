package com.minseoklim.toyproject2024.location.acceptance

import com.minseoklim.toyproject2024.test.util.RequestUtil
import com.minseoklim.toyproject2024.test.util.TestUtil.httpStatus
import io.restassured.response.ExtractableResponse
import io.restassured.response.Response
import org.assertj.core.api.Assertions.assertThat
import org.springframework.http.HttpStatus

@Suppress("ktlint:standard:function-naming")
object LocationAcceptanceTestFixture {
    fun `위치 등록 요청`(
        accessToken: String,
        request: Map<String, Any?>
    ): ExtractableResponse<Response> {
        return RequestUtil.put("/locations", accessToken, request)
    }

    fun `위치 등록됨`(response: ExtractableResponse<Response>) {
        assertThat(response.httpStatus()).isEqualTo(HttpStatus.OK)
    }

    fun `내 주변 위치 목록 조회 요청`(
        accessToken: String,
        radius: Int
    ): ExtractableResponse<Response> {
        return RequestUtil.get("/locations?radius=$radius", accessToken)
    }

    fun `내 주변 위치 목록 조회됨`(response: ExtractableResponse<Response>) {
        assertThat(response.httpStatus()).isEqualTo(HttpStatus.OK)
    }
}
