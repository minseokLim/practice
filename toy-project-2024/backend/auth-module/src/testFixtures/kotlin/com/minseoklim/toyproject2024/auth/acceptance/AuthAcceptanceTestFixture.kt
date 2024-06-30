package com.minseoklim.toyproject2024.auth.acceptance

import com.minseoklim.toyproject2024.auth.acceptance.AuthAcceptanceTestUtil.extractAccessToken
import com.minseoklim.toyproject2024.auth.acceptance.AuthAcceptanceTestUtil.extractRefreshToken
import com.minseoklim.toyproject2024.test.util.RequestUtil
import com.minseoklim.toyproject2024.test.util.TestUtil.httpStatus
import io.restassured.response.ExtractableResponse
import io.restassured.response.Response
import org.assertj.core.api.Assertions.assertThat
import org.springframework.http.HttpStatus

object AuthAcceptanceTestFixture {
    fun `로그인 요청`(request: Map<String, Any?>): ExtractableResponse<Response> {
        return RequestUtil.post("/login", null, request)
    }

    fun 로그인됨(response: ExtractableResponse<Response>) {
        assertThat(response.httpStatus()).isEqualTo(HttpStatus.OK)
        assertThat(response.extractAccessToken()).isNotNull
        assertThat(response.extractRefreshToken()).isNotNull
    }

    fun `토큰 유효성 검사 요청`(accessToken: String): ExtractableResponse<Response> {
        return RequestUtil.get("/validate-token", accessToken)
    }

    fun `토큰 유효성 검사됨`(response: ExtractableResponse<Response>) {
        assertThat(response.httpStatus()).isEqualTo(HttpStatus.OK)
    }

    fun `토큰 유효성 검사 실패`(response: ExtractableResponse<Response>) {
        assertThat(response.httpStatus()).isEqualTo(HttpStatus.UNAUTHORIZED)
    }

    fun `토큰 재발급 요청`(request: Map<String, Any?>): ExtractableResponse<Response> {
        return RequestUtil.post("/refresh-token", null, request)
    }

    fun `토큰 재발급됨`(response: ExtractableResponse<Response>) {
        assertThat(response.httpStatus()).isEqualTo(HttpStatus.OK)
        assertThat(response.extractAccessToken()).isNotNull
        assertThat(response.extractRefreshToken()).isNotNull
    }

    fun `로그아웃 요청`(request: Map<String, Any?>): ExtractableResponse<Response> {
        return RequestUtil.post("/logout", null, request)
    }

    fun 로그아웃됨(response: ExtractableResponse<Response>) {
        assertThat(response.httpStatus()).isEqualTo(HttpStatus.OK)
    }

    fun `로그아웃 실패`(response: ExtractableResponse<Response>) {
        assertThat(response.httpStatus()).isEqualTo(HttpStatus.UNAUTHORIZED)
    }

    fun `회원 전체 로그아웃 요청`(accessToken: String): ExtractableResponse<Response> {
        return RequestUtil.post("/logout-all", accessToken, emptyMap())
    }

    fun `회원 전체 로그아웃됨`(response: ExtractableResponse<Response>) {
        assertThat(response.httpStatus()).isEqualTo(HttpStatus.OK)
    }
}
