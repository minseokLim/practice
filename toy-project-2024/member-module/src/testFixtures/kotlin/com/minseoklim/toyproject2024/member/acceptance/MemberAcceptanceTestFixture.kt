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
}
