package com.minseoklim.toyproject2024.member.acceptance

import com.minseoklim.toyproject2024.test.util.RequestUtil
import com.minseoklim.toyproject2024.test.util.TestUtil.extractId
import com.minseoklim.toyproject2024.test.util.TestUtil.httpStatus
import io.restassured.response.ExtractableResponse
import io.restassured.response.Response
import org.assertj.core.api.Assertions.assertThat
import org.springframework.http.HttpStatus

object MemberAcceptanceTestFixture {
    fun `회원 가입 요청`(request: Map<String, Any?>): ExtractableResponse<Response> {
        return RequestUtil.post("/members", null, request)
    }

    fun `회원 가입됨`(response: ExtractableResponse<Response>) {
        assertThat(response.httpStatus()).isEqualTo(HttpStatus.CREATED)
        assertThat(response.extractId()).isNotNull
    }

    fun `회원 목록 조회 요청`(accessToken: String): ExtractableResponse<Response> {
        return RequestUtil.get("/members", accessToken)
    }

    fun `회원 목록 조회됨`(response: ExtractableResponse<Response>) {
        assertThat(response.httpStatus()).isEqualTo(HttpStatus.OK)
        assertThat(response.jsonPath().getList<Any>("content")).isNotEmpty
    }

    fun `회원 조회 요청`(accessToken: String, id: Int): ExtractableResponse<Response> {
        return RequestUtil.get("/members/$id", accessToken)
    }

    fun `회원 조회됨`(response: ExtractableResponse<Response>) {
        assertThat(response.httpStatus()).isEqualTo(HttpStatus.OK)
        assertThat(response.extractId()).isNotNull
    }

    fun `회원 탈퇴 요청`(accessToken: String, id: Int): ExtractableResponse<Response> {
        return RequestUtil.delete("/members/$id", accessToken)
    }

    fun `회원 탈퇴됨`(response: ExtractableResponse<Response>) {
        assertThat(response.httpStatus()).isEqualTo(HttpStatus.NO_CONTENT)
    }

    fun `내 계정 조회 요청`(accessToken: String): ExtractableResponse<Response> {
        return RequestUtil.get("/members/me", accessToken)
    }

    fun `내 계정 조회됨`(response: ExtractableResponse<Response>) {
        assertThat(response.httpStatus()).isEqualTo(HttpStatus.OK)
        assertThat(response.extractId()).isNotNull
    }

    fun `내 계정 수정 요청`(accessToken: String, request: Map<String, Any?>): ExtractableResponse<Response> {
        return RequestUtil.put("/members/me", accessToken, request)
    }

    fun `내 계정 수정됨`(response: ExtractableResponse<Response>) {
        assertThat(response.httpStatus()).isEqualTo(HttpStatus.OK)
        assertThat(response.extractId()).isNotNull
    }

    fun `내 계정 탈퇴 요청`(accessToken: String): ExtractableResponse<Response> {
        return RequestUtil.delete("/members/me", accessToken)
    }

    fun `내 계정 탈퇴됨`(response: ExtractableResponse<Response>) {
        assertThat(response.httpStatus()).isEqualTo(HttpStatus.NO_CONTENT)
    }

    fun `내 소셜 계정 연동 추가 요청`(accessToken: String, request: Map<String, Any?>): ExtractableResponse<Response> {
        return RequestUtil.post("/members/me/social-links", accessToken, request)
    }

    fun `내 소셜 계정 연동 추가됨`(response: ExtractableResponse<Response>) {
        assertThat(response.httpStatus()).isEqualTo(HttpStatus.OK)
    }

    fun `내 소셜 계정 연동 제거 요청`(accessToken: String, socialType: String): ExtractableResponse<Response> {
        return RequestUtil.delete("/members/me/social-links/$socialType", accessToken)
    }

    fun `내 소셜 계정 연동 제거됨`(response: ExtractableResponse<Response>) {
        assertThat(response.httpStatus()).isEqualTo(HttpStatus.NO_CONTENT)
    }
}
