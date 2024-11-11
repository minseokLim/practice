package com.minseoklim.toyproject2024.feed.acceptance

import com.minseoklim.toyproject2024.test.util.RequestUtil
import com.minseoklim.toyproject2024.test.util.TestUtil.httpStatus
import io.restassured.response.ExtractableResponse
import io.restassured.response.Response
import org.assertj.core.api.Assertions.assertThat
import org.springframework.http.HttpStatus
import java.io.File

@Suppress("ktlint:standard:function-naming")
object FeedAcceptanceTestFixture {
    fun `피드 등록 요청`(
        accessToken: String,
        files: List<File>,
        request: Map<String, Any?>
    ): ExtractableResponse<Response> {
        return RequestUtil.postWithMultipartFormData(
            path = "/feeds",
            accessToken = accessToken,
            fileParameterName = "files",
            files = files,
            requestParam = request,
        )
    }

    fun `피드 등록됨`(response: ExtractableResponse<Response>) {
        assertThat(response.httpStatus()).isEqualTo(HttpStatus.OK)
    }

    fun `피드 목록 조회 요청`(
        accessToken: String,
        size: Int
    ): ExtractableResponse<Response> {
        return RequestUtil.get("/feeds?size=$size", accessToken)
    }

    fun `피드 목록 조회됨`(response: ExtractableResponse<Response>) {
        assertThat(response.httpStatus()).isEqualTo(HttpStatus.OK)
    }
}
