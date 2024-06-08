package com.minseoklim.toyproject2024.auth.acceptance

import io.restassured.response.ExtractableResponse
import io.restassured.response.Response

object AuthAcceptanceTestUtil {
    fun ExtractableResponse<Response>.extractAccessToken(): String {
        return this.jsonPath().get("accessToken")
    }

    fun ExtractableResponse<Response>.extractRefreshToken(): String {
        return this.jsonPath().get("refreshToken")
    }
}
